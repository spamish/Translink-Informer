package com.spamish.project.translinkinformer.misc;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spamish.project.translinkinformer.models.Suggested;
import com.spamish.project.translinkinformer.models.Suggestion;
import com.spamish.project.translinkinformer.opia.TranslinkAPI;
import com.spamish.project.translinkinformer.opia.OpiaService;

import java.util.Timer;
import java.util.TimerTask;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LocationTextInput {
    AutoCompleteTextView text;
    Suggestion value;
    ArrayAdapter<Suggestion> adapter;
    Subscription subscription;
    Context context;
    View viewer;

    public LocationTextInput(Context context, View viewer, int resInput, final int resList, final int resSel) {
        this.context = context;
        this.viewer = viewer;

        Suggestion itemData = new Suggestion();
        Suggestion[] suggItemData = new Suggestion[1];
        suggItemData[0] = itemData;
        adapter = new SuggestAdapter(context, resList, resSel, suggItemData);

        text = (AutoCompleteTextView) viewer.findViewById(resInput);
        text.setAdapter(adapter);
        text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelativeLayout relLay = (RelativeLayout) view;
                TextView textView = (TextView) relLay.getChildAt(0);
                value = adapter.getItem(position);
                text.setText(textView.getText().toString());
            }
        });
        text.addTextChangedListener(new TextWatcher() {
            private Timer timer = new Timer();
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        resolveStartLocation(resList, resSel);
                    }
                },500);
            }
        });
    }

    private void resolveStartLocation(final int list, final int view) {
        final TranslinkAPI service = OpiaService.createTranslinkClient();

        subscription = service.getSuggest(text.getText().toString(), "0", 5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Suggested>() {
                    @Override public void onCompleted() {
                        subscription.unsubscribe();
                    }
                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Suggested response) {
                        int siz = response.getSuggestions().size();
                        Suggestion[] result = new Suggestion[siz];
                        adapter.notifyDataSetChanged();

                        for (int i = 0; i < siz; i++) {
                            result[i] = response.getSuggestions().get(i);
                        }

                        adapter = new SuggestAdapter(context, list, view, result);
                        text.setAdapter(adapter);
                    }
                });
    }

    public Suggestion getValue() {
        return value;
    }

    public String getText() {
        return value.getDescription();
    }
}
