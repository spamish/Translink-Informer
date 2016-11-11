package com.spamish.project.translinkinformer.frag_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spamish.project.translinkinformer.R;
import com.spamish.project.translinkinformer.api_tools.OpiaService;
import com.spamish.project.translinkinformer.api_tools.TranslinkAPI;
import com.spamish.project.translinkinformer.models.Route;
import com.spamish.project.translinkinformer.models.Routes;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TimetablesFragment extends Fragment {
    Subscription subs;

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
        View viewer = inflater.inflate(R.layout.fragment_timetables, container, false);

        final TextView textable = (TextView) viewer.findViewById(R.id.timetable_dump);
        Calendar cal = Calendar.getInstance();
        Date dateTime = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);

        final TranslinkAPI service = OpiaService.createTranslinkClient();

        subs = service.getRoutes(format.format(dateTime))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Routes>() {
                    @Override public void onCompleted() {
                        subs.unsubscribe();
                    }
                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Routes response) {
                        String data = "";

                        for (int i = 0; i < response.getRoutes().size(); i++) {
                            Route route = response.getRoutes().get(i);
                            data = data + route.getCode() + " " + route.getName() + "\n";

                            switch (route.getDirection()) {
                                case 0: //North
                                    data = data + "North\n";
                                    break;
                                case 1: // South
                                    data = data + "South\n";
                                    break;
                                case 2: // East
                                    data = data + "East\n";
                                    break;
                                case 3: // West
                                    data = data + "West\n";
                                    break;
                                case 4: // Inbound
                                    data = data + "Inbound\n";
                                    break;
                                case 5: // Outbound
                                    data = data + "Outbound\n";
                                    break;
                                case 6: // Inward
                                    data = data + "Inward\n";
                                    break;
                                case 7: // Outward
                                    data = data + "Outward\n";
                                    break;
                                case 8: // Upward
                                    data = data + "Upward\n";
                                    break;
                                case 9: // Downward
                                    data = data + "Downward\n";
                                    break;
                                case 10: // Clockwise
                                    data = data + "Clockwise\n";
                                    break;
                                case 11: // Counterclockwise
                                    data = data + "Counterclockwise\n";
                                    break;
                                case 12: // Direction1
                                    data = data + "Direction1\n";
                                    break;
                                case 13: // Direction2
                                    data = data + "Direction2\n";
                                    break;
                                default: // Null
                                    break;
                            }

                            switch (route.getServiceType()) {
                                case 1: //Regular
                                    data = data + "Regular\n";
                                    break;
                                case 2: // Express
                                    data = data + "Express\n";
                                    break;
                                case 4: // NightLink
                                    data = data + "NightLink\n";
                                    break;
                                case 8: // School
                                    data = data + "School\n";
                                    break;
                                case 16: // Industrial
                                    data = data + "Industrial\n";
                                    break;
                                default: // Null
                                    break;
                            }

                            switch (route.getVehicle()) {
                                case 2: //Bus
                                    data = data + "Bus\n";
                                    break;
                                case 4: // Ferry
                                    data = data + "Ferry\n";
                                    break;
                                case 8: // Train
                                    data = data + "Train\n";
                                    break;
                                case 16: // Walk
                                    data = data + "Walk\n";
                                    break;
                                case 32: // Tram
                                    data = data + "Tram\n";
                                    break;
                                default: // None
                                    data = data + "None\n";
                                    break;
                            }

                            data = data + "\n";
                        }
                        textable.setText(data);
                    }
                });

        return viewer;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
