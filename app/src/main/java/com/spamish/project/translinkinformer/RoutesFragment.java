package com.spamish.project.translinkinformer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RoutesFragment extends Fragment {
    private String title;
    private int page;

    public RoutesFragment() {
        // Required empty public constructor
    }

    public static RoutesFragment newInstance(int page, String title) {
        RoutesFragment fragmentRoute = new RoutesFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentRoute.setArguments(args);
        return fragmentRoute;
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
        View view = inflater.inflate(R.layout.fragment_routes, container, false);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
