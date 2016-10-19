package com.spamish.project.translinkinformer.misc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.spamish.project.translinkinformer.JourneyActivity;
import com.spamish.project.translinkinformer.R;
import com.spamish.project.translinkinformer.models.Leg;
import com.spamish.project.translinkinformer.models.Route;
import com.spamish.project.translinkinformer.models.Stop;
import com.spamish.project.translinkinformer.models.Stops;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import xdroid.toaster.Toaster;

/**
 * Created by spaj on 18/10/2016.
 */
public class LegAdaptor extends ArrayAdapter<Leg> {
    Stops stops;

    public LegAdaptor(JourneyActivity context, List<Leg> legs, Stops stops) {
        super(context, 0, legs);
        this.stops = stops;
    }

    @Override
    public View getView(int position, View converter, ViewGroup parent) {
        Leg leg = getItem(position);
        Route route = leg.getRoute();
        // Check if an existing view is being reused, otherwise inflate the view
        if (converter == null) {
            converter = LayoutInflater.from(getContext()).inflate(R.layout.list_legs, parent, false);
        }

        TextView viewDep = (TextView) converter.findViewById(R.id.view_dep_time);
        TextView viewArr = (TextView) converter.findViewById(R.id.view_arr_time);
        TextView viewRoute = (TextView) converter.findViewById(R.id.view_mode);

        String depTime = leg.getDepartureTime();
        String arrTime = leg.getArrivalTime();
        String instruction = leg.getInstruction();

        SimpleDateFormat format = new SimpleDateFormat("hh:mm a E d MMM", Locale.US);
        Date depDate = new Date(Long.parseLong(depTime.substring(
                depTime.indexOf("(") + 1,
                depTime.indexOf("+"))));
        Date arrDate = new Date(Long.parseLong(arrTime.substring(
                arrTime.indexOf("(") + 1,
                arrTime.indexOf("+"))));

        depTime = "Departing at: " + format.format(depDate);
        arrTime = "Arriving at: " + format.format(arrDate);

        String depId = leg.getFromStopId();
        String arrId = leg.getToStopId();
        String mode = getMode(route, depId, arrId);

        viewDep.setText(depTime);
        viewArr.setText(arrTime);
        viewRoute.setText(mode);

        return converter;
    }

    private String getMode(Route route, String from, String to) {
        String mode = "";
        Stop stop = new Stop();
        int ref, comp, s, siz = stops.getStops().size();

        if (from != null) {
            for (int i = 0; i < siz; i++) {
                ref = Integer.parseInt(from);
                comp = Integer.parseInt(stops.getStops().get(i).getStopId());
                if (ref == comp) {
                    stop = stops.getStops().get(i);
                }
            }

            from = " from " + stop.getDescription();
        } else {
            from = "";
        }

        if (to != null) {
            for (int i = 0; i < siz; i++) {
                ref = Integer.parseInt(to);
                comp = Integer.parseInt(stops.getStops().get(i).getStopId());
                if (ref == comp) {
                    stop = stops.getStops().get(i);
                }
            }

            to = " to " + stop.getDescription();
        } else {
            to = "";
        }

        if (route == null) {
            return "Walk" + from + to;
        }

        switch (route.getVehicle()) {
            case "2":
                mode = "bus";
                break;
            case "4":
                mode = "ferry";
                break;
            case "8":
                mode = "train";
                break;
            case "32":
                mode = "tram";
                break;
        }

        return ("Take the " + route.getCode()
                + " " + route.getName()
                + " " + mode
                + from
                + to + ".");
    }
}
