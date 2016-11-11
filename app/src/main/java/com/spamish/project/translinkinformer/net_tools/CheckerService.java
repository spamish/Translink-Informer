package com.spamish.project.translinkinformer.net_tools;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Base64;

import com.spamish.project.translinkinformer.R;
import com.spamish.project.translinkinformer.api_tools.PublicAPI;
import com.spamish.project.translinkinformer.api_tools.TwitterService;
import com.spamish.project.translinkinformer.models.Tweet;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterSession;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import io.fabric.sdk.android.Fabric;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xdroid.toaster.Toaster;

public class CheckerService extends IntentService {
    private Subscription twitSubs;
    SharedPreferences pref;
    private String since;
    String[] keywords = {
            "translink",   "traffic",     "crash",       "bus",
            "train",       "ferry",       "tram",        "accident",
            "car",         "truck",       "bike",        "delay"
    };

    public CheckerService() {
        super("CheckerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(
                getResources().getString(R.string.consumer_key),
                getResources().getString(R.string.consumer_sec)
        );

        Fabric.with(this, new Twitter(authConfig));
        TwitterSession session = Twitter.getInstance().core.getSessionManager().getActiveSession();
        TwitterAuthConfig consumer = Twitter.getInstance().core.getAuthConfig();

        if (session != null) {
            pref = PreferenceManager.getDefaultSharedPreferences(this);
            since = pref.getString("since", "0");

            String auth = getAuthentication(session.getUserId(), session.getUserName(), session.getAuthToken(), consumer);
            final PublicAPI service = TwitterService.createTwitterClient(auth);

            String sinceId = (since.equals("0") ? null : since);
            twitSubs = service.getTimeline(
                            String.valueOf(session.getUserId()),
                            session.getUserName(),
                            sinceId, true, true, false)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Tweet>>() {
                        @Override
                        public void onCompleted() {
                            twitSubs.unsubscribe();
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(List<Tweet> response) {
                            Boolean check;
                            String data;
                            Tweet last = null;

                            for (int i = 0; i < response.size(); i++) {
                                Tweet tweet = response.get(i);
                                check = false;

                                createNotification(
                                        i, "Translink Informer",
                                        since + " " + tweet.getIdStr()
                                );

                                for (String key : keywords) {
                                    if (tweet.getText().contains(key)) {
                                        check = true;
                                        break;
                                    }
                                }

                                Long ref = Long.parseLong(since);
                                Long comp = Long.parseLong(tweet.getIdStr());

                                if (check && (tweet.getCoordinates() != null) &&
                                        (comp > ref)) {
                                    data = "Tweet Id: " + tweet.getIdStr() + "\n"
                                            + "Date: " + tweet.getCreatedAt() + "\n"
                                            + "Lat: " + tweet.getCoordinates().getLat() + "\n"
                                            + "Lon: " + tweet.getCoordinates().getLon() + "\n"
                                            + "Text: " + tweet.getText() + "\n\n";

                                    // send data to server.
                                    Toaster.toast(data);
                                }

                                if (i == (response.size() - 1)) {
                                    last = tweet;
                                }
                            }

                            since = (last == null) ? since : last.getIdStr();
                            SharedPreferences.Editor edit = pref.edit();
                            edit.putString("since", since);
                            edit.apply();
                        }
                    });
        }
    }

    private void createNotification(int nId, String title, String body) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(title)
                .setContentText(body);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(nId, mBuilder.build());
    }

    private String getAuthentication(Long id, String name, TwitterAuthToken access, TwitterAuthConfig consumer) {

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[32];
        random.nextBytes(bytes);
        String rand = Base64.encodeToString(bytes, Base64.NO_WRAP);
        Long timestamp = System.currentTimeMillis()/1000;

        String method = "GET";

        String encUrl = "";
        String url = "https://api.twitter.com/1.1/statuses/user_timeline.json";

        String encPar = "";
        String params = "exclude_replies=true&" +
                "include_rts=false&" +
                "oauth_consumer_key=" + consumer.getConsumerKey() + "&" +
                "oauth_nonce=" + rand.replaceAll("[^A-Za-z0-9]", "") + "&" +
                "oauth_signature_method=HMAC-SHA1&" +
                "oauth_timestamp=" + timestamp.toString() + "&" +
                "oauth_token=" + access.token + "&" +
                "oauth_version=1.0&" +
                "screen_name=" + name + "&";

        if (!since.equals("0")) {
            params = params + "since_id=" + since + "&";
        }
        params = params +
                "trim_user=true&" +
                "user_id=" + id;

        try {
            encUrl = URLEncoder.encode(url, "UTF-8");
            encPar = URLEncoder.encode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String base = method + "&" + encUrl + "&" + encPar;

        String key = consumer.getConsumerSecret() + "&" + access.secret;

        String signature = "";
        String sig = sha1(base, key);

        try {
            signature = URLEncoder.encode(sig, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return  ("OAuth " +
                "oauth_consumer_key=\"" + consumer.getConsumerKey() + "\", " +
                "oauth_nonce=\"" + rand.replaceAll("[^A-Za-z0-9]", "") + "\", " +
                "oauth_signature=\"" + signature + "\", " +
                "oauth_signature_method=\"HMAC-SHA1\", " +
                "oauth_timestamp=\"" + timestamp.toString() + "\", " +
                "oauth_token=\"" + access.token + "\", " +
                "oauth_version=\"1.0\"");
    }

    public static String sha1(String s, String keyString) {
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(key);

            byte[] bytes = mac.doFinal(s.getBytes("UTF-8"));

            return Base64.encodeToString(bytes, Base64.NO_WRAP);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }
}
