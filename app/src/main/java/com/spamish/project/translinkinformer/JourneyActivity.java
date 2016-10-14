package com.spamish.project.translinkinformer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.spamish.project.translinkinformer.frag_supp.JourneyFragment;
import com.spamish.project.translinkinformer.models.Journeys;
import com.spamish.project.translinkinformer.models.TravelOptions;

import org.parceler.Parcels;

import xdroid.toaster.Toaster;

public class JourneyActivity extends AppCompatActivity {
    Journeys journeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        journeys = Parcels.unwrap(getIntent().getParcelableExtra("journeys"));
    }
}