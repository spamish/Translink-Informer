package com.spamish.project.translinkinformer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.spamish.project.translinkinformer.frag_supp.JourneyFragment;
import com.spamish.project.translinkinformer.models.Itinerary;
import com.spamish.project.translinkinformer.models.Journeys;
import com.spamish.project.translinkinformer.models.Stops;

import org.parceler.Parcels;

public class JourneyActivity extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;
    private Journeys journeys;
    private static int num_items;
    private Stops stops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        journeys = Parcels.unwrap(getIntent().getParcelableExtra("journeys"));
        int num = journeys.getTravelOptions().getItineraries().size();
        if (num > 5) {
            num_items = 5;
        } else {
            num_items = num;
        }

        stops = Parcels.unwrap(getIntent().getParcelableExtra("stops"));

        TextView viewFrom = (TextView) findViewById(R.id.view_from);
        TextView viewTo = (TextView) findViewById(R.id.view_to);
        String textFrom = Parcels.unwrap(getIntent().getParcelableExtra("from"));
        String textTo = Parcels.unwrap(getIntent().getParcelableExtra("to"));

        viewFrom.setText(textFrom);
        viewTo.setText(textTo);

        ViewPager journeyPager = (ViewPager) findViewById(R.id.view_pager);
        adapterViewPager = new JourneyAdaptor(getSupportFragmentManager());
        journeyPager.setAdapter(adapterViewPager);
        journeyPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        journeyPager.setCurrentItem(0);
    }

    public static class JourneyAdaptor extends FragmentPagerAdapter {

        public JourneyAdaptor(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return num_items;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return JourneyFragment.newInstance(0, "Option 1");
                case 1:
                    return JourneyFragment.newInstance(1, "Option 2");
                case 2:
                    return JourneyFragment.newInstance(2, "Option 3");
                case 3:
                    return JourneyFragment.newInstance(3, "Option 4");
                case 4:
                    return JourneyFragment.newInstance(4, "Option 5");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Option " + (position + 1);
        }

    }

    public Itinerary getItinerary(int pos) {
        return journeys.getTravelOptions().getItineraries().get(pos);
    }

    public Stops getStops() {
        return stops;
    }
}