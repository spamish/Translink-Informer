package com.spamish.project.translinkinformer.main_frag;

import java.util.Calendar;

import xdroid.toaster.Toaster;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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

import com.spamish.project.translinkinformer.R;
import com.spamish.project.translinkinformer.misc.LocationTextInput;
import com.spamish.project.translinkinformer.models.Suggestion;

public class PlannerFragment extends Fragment {
    private static final String TAG = "PlannerFragment";
    int pickYear, pickMonth, pickDay, pickHour, pickMin;
    Suggestion startVal, destVal;
    Button time, date;
    LocationTextInput startText;
    LocationTextInput destText;

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

        startText = new LocationTextInput(getActivity(), viewer,
                R.id.in_start, R.layout.list_loc_start, R.id.startTextViewItem);
        destText = new LocationTextInput(getActivity(), viewer,
                R.id.in_dest, R.layout.list_loc_dest, R.id.destTextViewItem);

        setupSpinnerSelection(viewer);
        setupTime();

        startVal = new Suggestion();
        startVal.setDescription("null");
        startVal.setId("null");
        destVal = new Suggestion();
        destVal.setDescription("null");
        destVal.setId("null");

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
                int modeVal = findModes(modeBus, modeTrain, modeFerry, modeTram);
                int typeVal = findTypes(typeReg, typeExp, typeNig, typeSch);
                int fareVal = findFares(fareStd, farePre, fareFre);

                int walkVal = findWalking(walking);
                int arrVal = arrival.getSelectedItemPosition();
                int speeVal = speed.getSelectedItemPosition();

                startVal = startText.getValue();
                destVal = destText.getValue();

                if (checkLocations()) {
                    //2016-09-09T13:05:00
                    String dateTimeVal = pickYear + "-" +
                            String.format("%02d", pickMonth + 1) + "-" +
                            String.format("%02d", pickDay) + "T" +
                            String.format("%02d", pickHour) + ":" +
                            String.format("%02d", pickMin) + ":00";

                    String text = "Start: " + startVal.getId() + "\n" +
                                  "Dest: " + destVal.getId() + "\n" +
                                  "Arrival: " + arrVal + "\n" +
                                  "Time: " + dateTimeVal + "\n" +
                                  "Vehicles: " + modeVal + "\n" +
                                  "Speed: " + speeVal + "\n" +
                                  "Walking: " + walkVal + "\n" +
                                  "Service: " + typeVal + "\n" +
                                  "Fare: " + fareVal;

                    Toaster.toast(text);
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
        pickYear = cal.get(Calendar.YEAR);
        pickMonth = cal.get(Calendar.MONTH);
        pickDay = cal.get(Calendar.DATE);
        pickHour = cal.get(Calendar.HOUR_OF_DAY);
        pickMin = cal.get(Calendar.MINUTE);

        updateTime(pickHour, pickMin);
        updateDate(pickYear, pickMonth, pickDay);
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

    private int findModes(CheckBox bus, CheckBox train, CheckBox ferry, CheckBox tram) {
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

        return value;
    }

    private int findTypes(CheckBox regular, CheckBox express, CheckBox nightlink, CheckBox school) {
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

        return value;
    }

    private int findFares(CheckBox standard, CheckBox prepaid, CheckBox free) {
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

        return value;
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
        switch (id) {
            case 0:
                return new DatePickerDialog(getActivity(), dateListener, pickYear, pickMonth, pickDay);
            case 1:
                return new TimePickerDialog(getActivity(), timeListener, pickHour, pickMin, false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dateListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker viewDate, int y, int m, int d) {
            updateDate(y, m, d);
        }
    };

    private void updateDate(int year, int month, int day) {
        pickYear = year;
        pickMonth = month;
        pickDay = day;

        String name = "", suffix = DomSuffix(day);
        switch (month) {
            case 0: name = "Jan"; break;
            case 1: name = "Feb"; break;
            case 2: name = "Mar"; break;
            case 3: name = "Apr"; break;
            case 4: name = "May"; break;
            case 5: name = "Jun"; break;
            case 6: name = "Jul"; break;
            case 7: name = "Aug"; break;
            case 8: name = "Sep"; break;
            case 9: name = "Oct"; break;
            case 10: name = "Nov"; break;
            case 11: name = "Dec"; break;
        }

        String dateString = new StringBuilder()
                .append(day)
                .append(suffix)
                .append(" ")
                .append(name)
                .append(" ")
                .append(year)
                .toString();
        date.setText(dateString);
    }

    private String DomSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }

    private TimePickerDialog.OnTimeSetListener timeListener
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker viewTime, int h, int m) {
            updateTime(h, m);
        }
    };

    private void updateTime(int hours, int minutes) {
        pickHour = hours;
        pickMin = minutes;

        String postfix, prefix = ":";
        if (hours > 11) {
            postfix = " pm";
            hours = hours - 12;
        } else {
            postfix = " am";
        }
        if (hours == 0) {
            hours = 12;
        }
        if (minutes < 10) {
            prefix = ":0";
        }

        String timeString = new StringBuilder()
                .append(hours)
                .append(prefix)
                .append(minutes)
                .append(postfix)
                .toString();
        time.setText(timeString);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
