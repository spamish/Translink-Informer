package com.spamish.project.translinkinformer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import xdroid.toaster.Toaster;

import com.spamish.project.translinkinformer.frag_main.BookmarksFragment;
import com.spamish.project.translinkinformer.frag_main.MapFragment;
import com.spamish.project.translinkinformer.frag_main.PlannerFragment;
import com.spamish.project.translinkinformer.frag_main.RoutesFragment;
import com.spamish.project.translinkinformer.frag_main.TimetablesFragment;
import com.spamish.project.translinkinformer.frag_main.TripsFragment;
import com.spamish.project.translinkinformer.net_tools.Connectivity;
import com.twitter.sdk.android.core.TwitterSession;

public class LandingActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout menuDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private int menuSel;
    private NavigationView navDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menuDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        menuDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navDrawer = (NavigationView) findViewById(R.id.nav_drawer);
        setupDrawerContent(navDrawer);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_content, new TripsFragment());
            transaction.commit();
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(
                this, menuDrawer, toolbar,
                R.string.drawer_open,  R.string.drawer_close
        );
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        int selection = menuItem.getItemId();
        FragmentTransaction transaction;
        Intent intent;

        switch(selection) {
            case R.id.nav_trips:
                if (menuSel != R.id.nav_trips) {
                    menuSel = R.id.nav_trips;
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_content, new TripsFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_routes:
                if (menuSel != R.id.nav_routes) {
                    menuSel = R.id.nav_routes;
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_content, new RoutesFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_bookmarks:
                if (menuSel != R.id.nav_bookmarks) {
                    menuSel = R.id.nav_bookmarks;
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_content, new BookmarksFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_planner:
                if (menuSel != R.id.nav_planner) {
                    menuSel = R.id.nav_planner;
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_content, new PlannerFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_timetables:
                if (menuSel != R.id.nav_timetables) {
                    menuSel = R.id.nav_timetables;
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_content, new TimetablesFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_map:
                if (menuSel != R.id.nav_map) {
                    menuSel = R.id.nav_map;
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_content, new MapFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            /**case R.id.nav_feedback:
                intent = new Intent(this, FeedbackActivity.class);
                startActivity(intent);
                break;*/
            case R.id.nav_help:
                intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        menuDrawer.closeDrawers();
    }

    @Override
    protected void onStart() {
        super.onStart();
        navDrawer.getMenu().performIdentifierAction(menuSel, 0);
        Connectivity connected = new Connectivity();
        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (!connected.isNetworkAvailable(conMan)) {
            Toaster.toast("Please check your internet connection");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    menuDrawer.openDrawer(GravityCompat.START);
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }

        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                menuDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
