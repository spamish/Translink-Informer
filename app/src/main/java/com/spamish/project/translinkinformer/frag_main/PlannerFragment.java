package com.spamish.project.translinkinformer.frag_main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xdroid.toaster.Toaster;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.spamish.project.translinkinformer.JourneyActivity;
import com.spamish.project.translinkinformer.R;
import com.spamish.project.translinkinformer.misc.LocationTextInput;
import com.spamish.project.translinkinformer.models.Itinerary;
import com.spamish.project.translinkinformer.models.Journeys;
import com.spamish.project.translinkinformer.models.Stops;
import com.spamish.project.translinkinformer.models.Suggestion;
import com.spamish.project.translinkinformer.api_tools.OpiaService;
import com.spamish.project.translinkinformer.api_tools.TranslinkAPI;

import org.parceler.Parcels;

public class PlannerFragment extends Fragment {
    Date dateTime;
    Suggestion startVal, destVal;
    Button time, date;
    LocationTextInput startText;
    LocationTextInput destText;
    Subscription tripSubs, stopsSubs;

    public PlannerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewer = inflater.inflate(R.layout.fragment_planner, container, false);

        final CheckBox modeBus = (CheckBox) viewer.findViewById(R.id.in_mode_bus);
        final CheckBox modeTrain = (CheckBox) viewer.findViewById(R.id.in_mode_train);
        final CheckBox modeFerry = (CheckBox) viewer.findViewById(R.id.in_mode_ferry);
        final CheckBox modeTram = (CheckBox) viewer.findViewById(R.id.in_mode_tram);

        final CheckBox typeReg = (CheckBox) viewer.findViewById(R.id.in_type_regular);
        final CheckBox typeExp = (CheckBox) viewer.findViewById(R.id.in_type_express);
        final CheckBox typeNig = (CheckBox) viewer.findViewById(R.id.in_type_nightlink);
        final CheckBox typeSch = (CheckBox) viewer.findViewById(R.id.in_type_school);

        final CheckBox fareStd = (CheckBox) viewer.findViewById(R.id.in_fare_standard);
        final CheckBox farePre = (CheckBox) viewer.findViewById(R.id.in_fare_prepaid);
        final CheckBox fareFre = (CheckBox) viewer.findViewById(R.id.in_fare_free);

        final Spinner arrival = (Spinner) viewer.findViewById(R.id.arrivalPicker);
        final Spinner speed = (Spinner) viewer.findViewById(R.id.speedPicker);
        final Spinner walking = (Spinner) viewer.findViewById(R.id.walkingPicker);

        date = (Button) viewer.findViewById(R.id.datePicker);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewer) {
                createDialog(0).show();
            }
        });

        time = (Button) viewer.findViewById(R.id.timePicker);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewer) {
                createDialog(1).show();
            }
        });

        Button submit = (Button) viewer.findViewById(R.id.submit);
        setupSubmitButton(submit, modeBus, modeTrain, modeFerry, modeTram,
                typeReg, typeExp, typeNig, typeSch, fareStd,
                farePre, fareFre, walking, arrival, speed);

        startText = new LocationTextInput(getActivity(), viewer, null,
                R.id.in_start, R.layout.list_loc_start, R.id.startTextViewItem);
        destText = new LocationTextInput(getActivity(), viewer, null,
                R.id.in_dest, R.layout.list_loc_dest, R.id.destTextViewItem);

        setupSpinnerSelection(viewer);
        setupTime();

        startVal = new Suggestion(null, null, 0);
        destVal = new Suggestion(null, null, 0);

        return viewer;
    }

    private void setupSubmitButton(Button button,
                                   final CheckBox modeBus,   final CheckBox modeTrain,
                                   final CheckBox modeFerry, final CheckBox modeTram,
                                   final CheckBox typeReg,   final CheckBox typeExp,
                                   final CheckBox typeNig,   final CheckBox typeSch,
                                   final CheckBox fareStd,   final CheckBox farePre,
                                   final CheckBox fareFre,   final Spinner walking,
                                   final Spinner arrival,    final Spinner speed) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewer) {
                String modeVal = findModes(modeBus, modeTrain, modeFerry, modeTram);
                String typeVal = findTypes(typeReg, typeExp, typeNig, typeSch);
                String fareVal = findFares(fareStd, farePre, fareFre);

                int walkVal = findWalking(walking);
                String arrVal = Integer.toString(arrival.getSelectedItemPosition());
                String speeVal = Integer.toString(speed.getSelectedItemPosition());

                startVal = startText.getValue();
                destVal = destText.getValue();

                if (checkLocations()) {
                    getTrips(startVal, destVal, arrVal, dateTime, modeVal, speeVal, walkVal, typeVal, fareVal);
                } else {
                    Toaster.toast("Please check the locations entered.");
                }
            }
        });
    }

    private void setupSpinnerSelection(View v) {
        Spinner arrival = (Spinner) v.findViewById(R.id.arrivalPicker);
        Spinner speed = (Spinner) v.findViewById(R.id.speedPicker);
        Spinner walking = (Spinner) v.findViewById(R.id.walkingPicker);

        arrival.setSelection(0);
        speed.setSelection(1);
        walking.setSelection(4);
    }

    private void setupTime() {
        Calendar cal = Calendar.getInstance();
        dateTime = cal.getTime();

        SimpleDateFormat format = new SimpleDateFormat("hh:mm a", Locale.US);
        String text = format.format(dateTime);
        time.setText(text);

        format = new SimpleDateFormat("E dd MM yyyy", Locale.US);
        text = format.format(dateTime);
        date.setText(text);
    }

    private boolean checkLocations() {
        try {
            String startDesc = startVal.getDescription();
            String startCheck = startText.getText();
            String destDesc = destVal.getDescription();
            String destCheck = destText.getText();
            boolean result = true;

            if (!startDesc.equals(startCheck)) {
                result = false;
            }
            if (!destDesc.equals(destCheck)) {
                result = false;
            }
            return result;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void getTrips(final Suggestion start, final Suggestion dest, String arrival, Date dateTime,
                          String mode, String speed, int walk, String type, String fare) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        final TranslinkAPI service = OpiaService.createTranslinkClient(getContext());

        tripSubs = service.getPlan(start.getId(), dest.getId(), arrival, format.format(dateTime),
                mode, speed, walk, type, fare)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Journeys>() {
                    @Override public void onCompleted() {
                        tripSubs.unsubscribe();
                    }
                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Journeys response) {
                        getStops(response, start, dest);
                    }
                });
    }

    private String findModes(CheckBox bus, CheckBox train, CheckBox ferry, CheckBox tram) {
        int value = 16;

        if (bus.isChecked()) {
            value += 2;
        }
        if (train.isChecked()) {
            value += 8;
        }
        if (ferry.isChecked()) {
            value += 4;
        }
        if (tram.isChecked()) {
            value += 32;
        }

        return Integer.toString(value);
    }

    private String findTypes(CheckBox regular, CheckBox express, CheckBox nightlink, CheckBox school) {
        int value = 0;

        if (regular.isChecked()) {
            value += 1;
        }
        if (express.isChecked()) {
            value += 2;
        }
        if (nightlink.isChecked()) {
            value += 4;
        }
        if (school.isChecked()) {
            value += 8;
        }

        return Integer.toString(value);
    }

    private String findFares(CheckBox standard, CheckBox prepaid, CheckBox free) {
        int value = 0;

        if (standard.isChecked()) {
            value += 2;
        }
        if (prepaid.isChecked()) {
            value += 4;
        }
        if (free.isChecked()) {
            value += 1;
        }

        return Integer.toString(value);
    }

    private int findWalking(Spinner w) {
        switch(w.getSelectedItemPosition()) {
            case 0:
                return 100;
            case 1:
                return 250;
            case 2:
                return 500;
            case 3:
                return 1000;
            case 4:
                return 1500;
            case 5:
                return 2000;
            case 6:
                return 4000;
            default:
                return 0;
        }
    }

    public Dialog createDialog(int id) {
        SimpleDateFormat format;
        int year, month, date, hour, min;

        switch (id) {
            case 0:
                format = new SimpleDateFormat("yyyy", Locale.US);
                year = Integer.parseInt(format.format(dateTime));
                format = new SimpleDateFormat("MM", Locale.US);
                month = Integer.parseInt(format.format(dateTime));
                format = new SimpleDateFormat("dd", Locale.US);
                date = Integer.parseInt(format.format(dateTime));

                return new DatePickerDialog(getActivity(), dateListener, year, month, date);
            case 1:
                format = new SimpleDateFormat("HH", Locale.US);
                hour = Integer.parseInt(format.format(dateTime));
                format = new SimpleDateFormat("mm", Locale.US);
                min = Integer.parseInt(format.format(dateTime));

                return new TimePickerDialog(getActivity(), timeListener, hour, min, false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dateListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker viewDate, int y, int m, int d) {
            SimpleDateFormat format = new SimpleDateFormat("HH", Locale.US);
            int h = Integer.parseInt(format.format(dateTime));
            format = new SimpleDateFormat("mm", Locale.US);
            int k = Integer.parseInt(format.format(dateTime));

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, y);
            cal.set(Calendar.MONTH, m);
            cal.set(Calendar.DATE, d);
            cal.set(Calendar.HOUR_OF_DAY, h);
            cal.set(Calendar.MINUTE, k);
            dateTime = cal.getTime();

            format = new SimpleDateFormat("E dd MM yyyy", Locale.US);
            String text = format.format(dateTime);

            date.setText(text);
        }
    };

    private TimePickerDialog.OnTimeSetListener timeListener
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker viewTime, int h, int k) {
            SimpleDateFormat format = new SimpleDateFormat("yyy", Locale.US);
            int y = Integer.parseInt(format.format(dateTime));
            format = new SimpleDateFormat("MM", Locale.US);
            int m = Integer.parseInt(format.format(dateTime));
            format = new SimpleDateFormat("dd", Locale.US);
            int d = Integer.parseInt(format.format(dateTime));

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, y);
            cal.set(Calendar.MONTH, m);
            cal.set(Calendar.DATE, d);
            cal.set(Calendar.HOUR_OF_DAY, h);
            cal.set(Calendar.MINUTE, k);
            dateTime = cal.getTime();

            format = new SimpleDateFormat("hh:mm a", Locale.US);
            String text = format.format(dateTime);

            time.setText(text);
        }
    };

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

        final TranslinkAPI service = OpiaService.createTranslinkClient(getContext());

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
