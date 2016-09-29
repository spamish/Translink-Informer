package com.spamish.project.translinkinformer.frag_supp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spamish.project.translinkinformer.JourneyActivity;
import com.spamish.project.translinkinformer.R;
import com.spamish.project.translinkinformer.models.TravelOptions;

import xdroid.toaster.Toaster;

public class JourneyFragment extends Fragment {

    public JourneyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewer = inflater.inflate(R.layout.fragment_journey, container, false);

        JourneyActivity activity = (JourneyActivity) getActivity();
        TravelOptions data;

        data = activity.getJourneys();
        Toaster.toast(data.toString());

        return viewer;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
