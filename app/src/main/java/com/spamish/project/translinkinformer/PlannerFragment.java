package com.spamish.project.translinkinformer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class PlannerFragment extends Fragment {

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
        // Inflate the layout for this fragment

        final EditText startText = (EditText) viewer.findViewById(R.id.in_start);
        final EditText destText = (EditText) viewer.findViewById(R.id.in_destination);

        Button date = (Button) viewer.findViewById(R.id.datePicker);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewer) {
                createDate(0).show();
            }
        });

        Button time = (Button) viewer.findViewById(R.id.timePicker);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewer) {
                createDate(1).show();
            }
        });


        Button submit = (Button) viewer.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewer) {
                String startVal = startText.getText().toString();
                String destVal = destText.getText().toString();
            }
        });

        return viewer;
    }

    public Dialog createDate(int id) {
        switch (id) {
            case 0:
                //return new TimePickerDialog(getActivity(), timePickerListener, hour, minute, false);
                break;
            case 1:
                //return new DatePickerDialog(getActivity(), datePickerListener, day, month, false);
                break;
            default:
                break;
        }

        return null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
