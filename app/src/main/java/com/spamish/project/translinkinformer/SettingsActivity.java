package com.spamish.project.translinkinformer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class SettingsActivity extends AppCompatActivity {
    Boolean stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        SharedPreferences settings = this.getSharedPreferences("settings", 0);
        String status = settings.getString("status", "false");

        TextView login = (TextView) findViewById(R.id.text_status);
        if (!status.equals("false")) {
            login.setText("Logged in as: ");
            // get username
            // get login details
            // login to twitter and save information
            stat = true;
        } else {
            login.setText("Not logged in");
            // display login section
            stat = false;
        }

    }
}
