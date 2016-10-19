package com.spamish.project.translinkinformer.frag_supp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.spamish.project.translinkinformer.JourneyActivity;
import com.spamish.project.translinkinformer.R;
import com.spamish.project.translinkinformer.misc.LegAdaptor;
import com.spamish.project.translinkinformer.models.Itinerary;
import com.spamish.project.translinkinformer.models.Leg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import xdroid.toaster.Toaster;

public class JourneyFragment extends Fragment {
    private String title;
    private int page;

    public static JourneyFragment newInstance(int page, String title) {
        JourneyFragment journeyFrag = new JourneyFragment();
        Bundle args = new Bundle();

        args.putInt("pageNum", page);
        args.putString("pageTitle", title);
        journeyFrag.setArguments(args);

        return journeyFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("pageNum", 0);
        title = getArguments().getString("pageTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewer = inflater.inflate(R.layout.fragment_journey, container, false);

        TextView viewDuration = (TextView) viewer.findViewById(R.id.view_duration);
        TextView viewStartTime = (TextView) viewer.findViewById(R.id.view_start_time);
        TextView viewEndTime = (TextView) viewer.findViewById(R.id.view_end_time);

        JourneyActivity journey = (JourneyActivity) getActivity();
        Itinerary itinerary = journey.getItinerary(page);

        String duration = itinerary.getDurationMins().toString();
        String transfers = itinerary.getTransfers().toString();
        String startTime = itinerary.getStartTime();
        String endTime = itinerary.getEndTime();
        String text = "Duration: "
                + duration
                + " mins, with "
                + transfers
                + " transfer";

        SimpleDateFormat format = new SimpleDateFormat("hh:mm a E d MMM", Locale.US);
        Date startDate = new Date(Long.parseLong(startTime.substring(
                        startTime.indexOf("(") + 1,
                        startTime.indexOf("+"))));
        Date endDate = new Date(Long.parseLong(endTime.substring(
                        endTime.indexOf("(") + 1,
                        endTime.indexOf("+"))));

        startTime = "Start time: " + format.format(startDate);
        endTime = "Finish time: " + format.format(endDate);

        viewDuration.setText(text);
        viewStartTime.setText(startTime);
        viewEndTime.setText(endTime);

        List<Leg> journeyLegs = itinerary.getLegs();
        LegAdaptor adapter = new LegAdaptor(journey, journeyLegs, journey.getStops());
        ListView listView = (ListView) viewer.findViewById(R.id.list_view_legs);
        listView.setAdapter(adapter);

        return viewer;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
