package com.spamish.project.translinkinformer.frag_main;

import java.util.Timer;
import java.util.TimerTask;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spamish.project.translinkinformer.R;
import com.spamish.project.translinkinformer.misc.SuggestAdapter;
import com.spamish.project.translinkinformer.models.Suggested;
import com.spamish.project.translinkinformer.models.Suggestion;
import com.spamish.project.translinkinformer.opia.TranslinkAPI;
import com.spamish.project.translinkinformer.opia.OpiaService;

public class MapFragment extends Fragment {
    Suggestion locVal;
    Subscription subscription;
    AutoCompleteTextView locText;
    ArrayAdapter<Suggestion> locAdapter;
    TextView dataDump;

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
        locText = (AutoCompleteTextView) viewer.findViewById(R.id.in_loc);
        setupLocationChecker();
        Suggestion itemData = new Suggestion();
        Suggestion[] suggItemData = new Suggestion[1];
        suggItemData[0] = itemData;
        locAdapter = new SuggestAdapter(getActivity(), R.layout.list_locations, R.id.textViewItem, suggItemData);
        locText.setAdapter(locAdapter);

        dataDump = (TextView) viewer.findViewById(R.id.data_dump);
        locVal = new Suggestion(null, null, 0);
        return viewer;
    }

    private void setupLocationChecker() {

        locText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelativeLayout relLay = (RelativeLayout) view;
                TextView textView = (TextView) relLay.getChildAt(0);
                locVal = locAdapter.getItem(position);
                locText.setText(textView.getText().toString());
                dataDump.setText(locVal.getDescription() + "\n" + locVal.getId());
            }
        });
        locText.addTextChangedListener(new TextWatcher() {
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
        final TranslinkAPI service = OpiaService.createTranslinkClient();

        subscription = service.getSuggest(locText.getText().toString(), "0", 5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Suggested>() {
                    @Override public void onCompleted() {
                        subscription.unsubscribe();
                    }
                    @Override public void onError(Throwable e) {
                        dataDump.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(Suggested response) {
                        int siz = response.getSuggestions().size();
                        Suggestion[] result = new Suggestion[siz];
                        locAdapter.notifyDataSetChanged();

                        for (int i = 0; i < siz; i++) {
                            result[i] = response.getSuggestions().get(i);
                        }

                        locAdapter = new SuggestAdapter(getActivity(), R.layout.list_locations, R.id.textViewItem, result);
                        locText.setAdapter(locAdapter);
                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
