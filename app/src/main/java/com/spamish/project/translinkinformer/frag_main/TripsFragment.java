package com.spamish.project.translinkinformer.frag_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.spamish.project.translinkinformer.JourneyActivity;
import com.spamish.project.translinkinformer.R;
import com.spamish.project.translinkinformer.models.Itinerary;
import com.spamish.project.translinkinformer.models.Journeys;
import com.spamish.project.translinkinformer.models.Stops;
import com.spamish.project.translinkinformer.models.Suggestion;
import com.spamish.project.translinkinformer.api_tools.OpiaService;
import com.spamish.project.translinkinformer.api_tools.TranslinkAPI;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TripsFragment extends Fragment {
    Subscription tripsSubs, stopsSubs;

    public TripsFragment() {
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
        View viewer = inflater.inflate(R.layout.fragment_trips, container, false);

        Calendar cal = Calendar.getInstance();
        final Date dateTime = cal.getTime();
        final Suggestion start, dest;

        start = new Suggestion("26 Latrobe St, East Brisbane", "AD:26 Latrobe St^ East Brisbane", 2);
        dest = new Suggestion("106 Merthyr Rd, New Farm", "AD:106 Merthyr Rd^ New Farm", 2);

        Button submit = (Button) viewer.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewer) {
                getTrips(start, dest, "0", dateTime, "62", "1", 4000, "15", "7");
            }
        });

        return viewer;
    }

    private void getTrips(final Suggestion start, final Suggestion dest, String arrival, Date dateTime,
                          String mode, String speed, int walk, String type, String fare) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        final TranslinkAPI service = OpiaService.createTranslinkClient();

        tripsSubs = service.getPlan(start.getId(), dest.getId(), arrival, format.format(dateTime),
                mode, speed, walk, type, fare)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Journeys>() {
                    @Override public void onCompleted() {
                        tripsSubs.unsubscribe();
                    }
                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override public void onNext(Journeys response) {
                        getStops(response, start, dest);
                    }
                });
    }

    private void getStops(final Journeys journeys, final Suggestion start, final Suggestion dest) {
        String stop, codes = "";

        int num = journeys.getTravelOptions().getItineraries().size();

        for (int i = 0; i < num; i++) {
            Itinerary itinerary = journeys.getTravelOptions().getItineraries().get(i);
            int siz = itinerary.getLegs().size();

            for (int j = 0; j < siz; j++) {
                stop = itinerary.getLegs().get(j).getFromStopId();
                if ((stop != null) && (!codes.contains(stop))) {
                    codes = codes + "," + stop;
                }

                 stop = itinerary.getLegs().get(j).getToStopId();
                 if ((stop != null) && (!codes.contains(stop))) {
                    codes = codes + "," + stop;
                 }
            }
        }

        if (codes.equals("")) {
             Intent intent = new Intent(getActivity(), JourneyActivity.class);
             String startText = start.getDescription();
             String destText = dest.getDescription();

             intent.putExtra("journeys", Parcels.wrap(journeys));
             intent.putExtra("from", Parcels.wrap(startText));
             intent.putExtra("to", Parcels.wrap(destText));
             intent.putExtra("stops", Parcels.wrap(new Stops()));

             startActivity(intent);
        }

        codes = codes.substring(1);

        final TranslinkAPI service = OpiaService.createTranslinkClient();

        stopsSubs = service.getStopList(codes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Stops>() {
                    @Override public void onCompleted() {
                        stopsSubs.unsubscribe();
                    }
                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Stops response) {
                        Intent intent = new Intent(getActivity(), JourneyActivity.class);
                        String startText = start.getDescription();
                        String destText = dest.getDescription();

                        intent.putExtra("journeys", Parcels.wrap(journeys));
                        intent.putExtra("from", Parcels.wrap(startText));
                        intent.putExtra("to", Parcels.wrap(destText));
                        intent.putExtra("stops", Parcels.wrap(response));

                        startActivity(intent);
                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
