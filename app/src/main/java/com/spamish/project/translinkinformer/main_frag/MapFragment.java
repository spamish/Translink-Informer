package com.spamish.project.translinkinformer.main_frag;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.spamish.project.translinkinformer.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment {
    boolean locRes;
    String locVal;

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
        //final String[] DATA = new String[] {"BDSM", "Big Tits", "Lesbian", "Teen", "MILF"};

        final AutoCompleteTextView locText = (AutoCompleteTextView) viewer.findViewById(R.id.in_loc);
        locText.setAdapter(new AutoCompleteAdapter(getActivity()));
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, DATA);
        //locText.setAdapter(adapter);

        //setupLocationChecker(viewer);
        locRes = true;

        return viewer;
    }

    /*private void setupLocationChecker(View v) {
        final AutoCompleteTextView locText = (AutoCompleteTextView) v.findViewById(R.id.in_loc);

        locText.addTextChangedListener(new TextWatcher() {
            private Timer startTime = new Timer();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (locRes) {
                    startTime.cancel();
                    startTime = new Timer();
                    startTime.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            resolveLocation(locText);
                        }
                    }, 1000);
                }
            }
        });
    }

    private void resolveLocation(final EditText text) {
        String value = text.getText().toString();
        final LocationAPI service = OpiaService.createLocationClient();

        subscription = service.getSuggest(value, "0", 5)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }

    Observer<Suggested> observable = new Observer<Suggested>() {

        @Override
        public void onCompleted() {
            subscription.unsubscribe();
        }

        @Override
        public void onError(Throwable e) {
            Toaster.toast(e.getMessage());
        }

        @Override
        public void onNext(Suggested suggestions) {
            Toaster.toast(suggestions.getSuggestion().get(0).getDescription()
                    + "\n" + suggestions.getSuggestion().get(0).getId());
        }
    };*/

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class AutoCompleteAdapter extends ArrayAdapter<Address> implements Filterable {

        private LayoutInflater mInflater;
        private Geocoder mGeocoder;
        private StringBuilder mSb = new StringBuilder();

        public AutoCompleteAdapter(final Context context) {
            super(context, -1);
            mInflater = LayoutInflater.from(context);
            mGeocoder = new Geocoder(context);
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            final TextView tv;
            if (convertView != null) {
                tv = (TextView) convertView;
            } else {
                tv = (TextView) mInflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
            }

            tv.setText(createFormattedAddressFromAddress(getItem(position)));
            return tv;
        }

        private String createFormattedAddressFromAddress(final Address address) {
            mSb.setLength(0);
            final int addressLineSize = address.getMaxAddressLineIndex();
            for (int i = 0; i < addressLineSize; i++) {
                mSb.append(address.getAddressLine(i));
                if (i != addressLineSize - 1) {
                    mSb.append(", ");
                }
            }
            return mSb.toString();
        }

        @Override
        public Filter getFilter() {
            Filter myFilter = new Filter() {
                @Override
                protected FilterResults performFiltering(final CharSequence constraint) {
                    List<Address> addressList = null;
                    if (constraint != null) {
                        try {
                            addressList = mGeocoder.getFromLocationName((String) constraint, 5);
                        } catch (IOException e) {
                        }
                    }
                    if (addressList == null) {
                        addressList = new ArrayList<>();
                    }

                    final FilterResults filterResults = new FilterResults();
                    filterResults.values = addressList;
                    filterResults.count = addressList.size();

                    return filterResults;
                }

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(final CharSequence contraint, final FilterResults results) {
                    clear();
                    for (Address address : (List<Address>) results.values) {
                        add(address);
                    }
                    if (results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }

                @Override
                public CharSequence convertResultToString(final Object resultValue) {
                    return resultValue == null ? "" : ((Address) resultValue).getAddressLine(0);
                }
            };
            return myFilter;
        }
    }
}