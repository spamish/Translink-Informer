package com.spamish.project.translinkinformer.api_tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwitterService {

    private final static String TWITTER_URL = "https://api.twitter.com/1.1/";

    public TwitterService(){
    }

    public static PublicAPI createTwitterClient(final String oauth) {

        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", oauth)
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

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TWITTER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .build();
        return retrofit.create(PublicAPI.class);
    }
}