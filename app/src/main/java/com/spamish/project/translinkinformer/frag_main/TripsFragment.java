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
import com.spamish.project.translinkinformer.misc.SuggestAdapter;
import com.spamish.project.translinkinformer.models.Journeys;
import com.spamish.project.translinkinformer.models.Suggested;
import com.spamish.project.translinkinformer.models.Suggestion;
import com.spamish.project.translinkinformer.opia.OpiaService;
import com.spamish.project.translinkinformer.opia.TranslinkAPI;

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
    Subscription subscription;

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

    private void getTrips(Suggestion start, Suggestion dest, String arrival, Date dateTime,
                          String mode, String speed, int walk, String type, String fare) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        final TranslinkAPI service = OpiaService.createTranslinkClient();

        subscription = service.getPlan(start.getId(), dest.getId(), arrival, format.format(dateTime),
                mode, speed, walk, type, fare)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Journeys>() {
                    @Override public void onCompleted() {
                        subscription.unsubscribe();
                    }
                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Journeys response) {
                        Intent intent = new Intent(getActivity(), JourneyActivity.class);
                        intent.putExtra("journeys", Parcels.wrap(response));
                        startActivity(intent);
                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
