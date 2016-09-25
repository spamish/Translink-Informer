package com.spamish.project.translinkinformer.main_frag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spamish.project.translinkinformer.R;

public class TimetablesFragment extends Fragment {

    public TimetablesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timetables, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
