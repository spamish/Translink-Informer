package com.spamish.project.translinkinformer;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private int menuSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle = setupDrawerToggle();
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        if (savedInstanceState == null) {
            menuSel = R.id.nav_trips;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.content, new TripsFragment());
            transaction.commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        nvDrawer.getMenu().performIdentifierAction(menuSel, 0);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
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
                    transaction.replace(R.id.content, new TripsFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_routes:
                if (menuSel != R.id.nav_routes) {
                    menuSel = R.id.nav_routes;
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, new RoutesFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_bookmarks:
                if (menuSel != R.id.nav_bookmarks) {
                    menuSel = R.id.nav_bookmarks;
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, new BookmarksFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_planner:
                if (menuSel != R.id.nav_feedback) {
                    menuSel = R.id.nav_feedback;
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, new PlannerFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_timetables:
                if (menuSel != R.id.nav_timetables) {
                    menuSel = R.id.nav_timetables;
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, new TimetablesFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_map:
                if (menuSel != R.id.nav_map) {
                    menuSel = R.id.nav_map;
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, new MapFragment());
                    transaction.commit();
                }
                break;
            case R.id.nav_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_feedback:
                intent = new Intent(this, FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_help:
                intent = new Intent(this, SettingsActivity.class);
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
        mDrawer.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    mDrawer.openDrawer(GravityCompat.START);
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }

        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
