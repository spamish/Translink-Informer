package com.spamish.project.translinkinformer.frag_main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.spamish.project.translinkinformer.R;
import com.spamish.project.translinkinformer.api_tools.OpiaService;
import com.spamish.project.translinkinformer.api_tools.TranslinkAPI;
import com.spamish.project.translinkinformer.misc.LocationTextInput;
import com.spamish.project.translinkinformer.models.Nearby;
import com.spamish.project.translinkinformer.models.NearbyStop;
import com.spamish.project.translinkinformer.models.Stop;
import com.spamish.project.translinkinformer.models.Stops;
import com.spamish.project.translinkinformer.models.Suggestion;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xdroid.toaster.Toaster;

public class MapFragment extends Fragment {
    LocationTextInput locText;
    TextView dataDump;
    Subscription nearbySubs, stopsSubs;
    List<NearbyStop> nearby;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewer = inflater.inflate(R.layout.fragment_map, container, false);

        dataDump = (TextView) viewer.findViewById(R.id.data_dump);
        locText = new LocationTextInput(getActivity(), viewer, this,
                R.id.in_loc, R.layout.list_locations, R.id.locTextViewItem);

        return viewer;
    }

    public void getStopsNearby(Suggestion selected) {
        final TranslinkAPI service = OpiaService.createTranslinkClient();
        Toaster.toast("Got location");

        nearbySubs = service.getNearby(selected.getId(), 500, true, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Nearby>() {
                    @Override public void onCompleted() {
                        nearbySubs.unsubscribe();
                    }
                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Nearby response) {
                        nearby = response.getNearbyStops();
                        resolveStops();
                    }
                });
    }

    private void resolveStops() {
        String codes = "";
        Toaster.toast("Got stops");

        for (int i = 0; i < nearby.size(); i++) {
            codes = codes + "," + nearby.get(i).getStopId();
        }

        codes = codes.substring(1);
        Toaster.toast(codes);
        final TranslinkAPI service = OpiaService.createTranslinkClient();

        stopsSubs = service.getStopList(codes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Stops>() {
                    @Override public void onCompleted() { stopsSubs.unsubscribe();
                    }
                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Stops response) {
                        List<Stop> stops = response.getStops();
                        String data = "";
                        Toaster.toast("Got stop");

                        for (int i = 0; i < nearby.size(); i++) {
                            data = data + stops.get(i).getDescription() + "\n" +
                                    nearby.get(i).getDistance().getDistanceM() + "m\n" +
                                    nearby.get(i).getStopId() + " " + stops.get(i).getStopId() +
                                    "\n" + stops.get(i).getSuburb() + ", zone " +
                                    stops.get(i).getZone() + "\n\n";
                        }

                        dataDump.setText(data);
                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
