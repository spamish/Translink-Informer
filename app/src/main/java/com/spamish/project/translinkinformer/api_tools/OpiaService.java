package com.spamish.project.translinkinformer.api_tools;

import android.content.Context;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spamish.project.translinkinformer.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xdroid.toaster.Toaster;

public class OpiaService {

    private final static String OPIA_URL = "https://opia.api.translink.com.au/v2/";

    public OpiaService(){
    }

    public static TranslinkAPI createTranslinkClient(Context context) {

        String credentials = context.getResources().getString(R.string.credentials);
        final String basic =
                "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", basic)
                        .addHeader("Accept", "application/json")
                        .build();
                return chain.proceed(newRequest);
            }
        };

        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        builder.interceptors().add(logger);
        builder.connectTimeout(30, TimeUnit.SECONDS); // connect timeout
        builder.readTimeout(30, TimeUnit.SECONDS);    // socket timeout

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OPIA_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .build();
        return retrofit.create(TranslinkAPI.class);
    }
}
