package com.spamish.project.translinkinformer.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Filterable;

import com.spamish.project.translinkinformer.models.Suggestion;

import java.util.List;

public class LocationAdaptor extends ArrayAdapter<Suggestion> implements Filterable {

    List<Suggestion> arraySuggestion;
    private LayoutInflater layoutInflater;

    public LocationAdaptor(Context context) {
        super(context, -1);
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}