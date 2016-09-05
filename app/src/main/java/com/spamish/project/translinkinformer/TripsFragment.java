package com.spamish.project.translinkinformer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TripsFragment extends Fragment {
    private String title;
    private int page;

    public TripsFragment() {
        // Required empty public constructor
    }

    public static TripsFragment newInstance(int page, String title) {
        TripsFragment fragmentTrip = new TripsFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentTrip.setArguments(args);
        return fragmentTrip;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trips, container, false);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
