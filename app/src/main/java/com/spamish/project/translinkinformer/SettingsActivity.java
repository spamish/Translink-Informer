package com.spamish.project.translinkinformer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.spamish.project.translinkinformer.net_tools.AlarmReceiver;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;
import xdroid.toaster.Toaster;

public class SettingsActivity extends AppCompatActivity {
    private TwitterLoginButton loginButton;
    private TextView textView;
    private TwitterSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(
                getResources().getString(R.string.consumer_key),
                getResources().getString(R.string.consumer_sec)
        );
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.text_status);
        textView.setVisibility(View.INVISIBLE);
        session = Twitter.getInstance().core.getSessionManager().getActiveSession();

        if (session == null) {
            textView.setVisibility(View.INVISIBLE);
            loginButton.setVisibility(View.VISIBLE);
            loginButton.setCallback(new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    session = result.data;
                    Toaster.toast(session.toString());
                    loginButton.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(
                            "User Name: " + session.getUserName() + "\n" +
                            "User ID: " + session.getUserId()
                    );

                    Intent i = new Intent(getApplicationContext(), AlarmReceiver.class);
                    final PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), AlarmReceiver.REQUEST_CODE,
                            i, PendingIntent.FLAG_UPDATE_CURRENT);

                    long firstMillis = System.currentTimeMillis();
                    AlarmManager alarm = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                    alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                            AlarmManager.INTERVAL_FIFTEEN_MINUTES, pending);
                }
                @Override
                public void failure(TwitterException exception) {
                    Log.d("TwitterKit", "Login with Twitter failure", exception);
                }
            });
        } else {
            loginButton.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView.setText(
                    "User Name: " + session.getUserName() + "\n" +
                    "User ID: " + session.getUserId()
            );

            Intent i = new Intent(getApplicationContext(), AlarmReceiver.class);
            boolean running = (PendingIntent.getBroadcast(getApplicationContext(), 0,
                    i, PendingIntent.FLAG_NO_CREATE) != null);

            if (!running) {
                final PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), AlarmReceiver.REQUEST_CODE,
                        i, PendingIntent.FLAG_UPDATE_CURRENT);

                long firstMillis = System.currentTimeMillis();
                AlarmManager alarm = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                        AlarmManager.INTERVAL_FIFTEEN_MINUTES, pending);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

}
