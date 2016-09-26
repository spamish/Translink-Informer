package com.spamish.project.translinkinformer.main_frag;

import java.util.Timer;
import java.util.TimerTask;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xdroid.toaster.Toaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.spamish.project.translinkinformer.R;
import com.spamish.project.translinkinformer.misc.LocationAdaptor;
import com.spamish.project.translinkinformer.models.Suggested;
import com.spamish.project.translinkinformer.models.Suggestion;
import com.spamish.project.translinkinformer.opia.LocationAPI;
import com.spamish.project.translinkinformer.opia.OpiaService;

public class MapFragment extends Fragment {
    Suggestion startVal;
    Subscription subscription;
    AutoCompleteTextView startText;
    TextView dataDump;

    private static final String[] DATA = new String[] {
            "Big Tits", "Lesbian", "MILF", "Teen", "Toys"
    };

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
        startText = (AutoCompleteTextView) viewer.findViewById(R.id.in_loc);
        dataDump = (TextView) viewer.findViewById(R.id.data_dump);
        startText.setAdapter(new LocationAdaptor(getActivity()));

        setupLocationChecker();

        startVal = new Suggestion(null, null, 0);
        return viewer;
    }

    private void setupLocationChecker() {

        startText.addTextChangedListener(new TextWatcher() {
            private Timer startTime = new Timer();

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                startTime.cancel();
                startTime = new Timer();
                startTime.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        resolveStartLocation();
                    }
                },1000);
            }
        });
    }

    private void resolveStartLocation() {
        final LocationAPI service = OpiaService.createLocationClient();

        subscription = service.getSuggest(startText.getText().toString(), "0", 5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Suggested>() {
                    @Override
                    public void onCompleted() {
                        subscription.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toaster.toast(e.getMessage());
                    }

                    @Override
                    public void onNext(Suggested response) {
                        int siz = response.getSuggestions().size();
                        StringBuilder text = new StringBuilder();

                        for (int i = 0; i < siz; i++) {
                            Suggestion result = response.getSuggestions().get(i);
                            text.append(result.getDescription() + "\n" + result.getId() + "\n");
                        }
                        dataDump.setText(text.toString());
                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
