package com.spamish.project.translinkinformer.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.spamish.project.translinkinformer.models.Suggestion;

public class SuggestAdapter extends ArrayAdapter<Suggestion> {
    Context context;
    int layoutResourceId, textView;
    Suggestion data[] = null;

    public SuggestAdapter(Context context, int layoutResourceId, int textView, Suggestion[] data) {

        super(context, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.textView = textView;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try{

	        /*
	         * The convertView argument is essentially a "ScrapView" as described is Lucas post
	         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
	         * It will have a non-null value when ListView is asking you recycle the row layout.
	         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
	         */
            if(convertView==null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(layoutResourceId, parent, false);
            }

            // object item based on the position
            Suggestion objectItem = data[position];

            // get the TextView and then set the text (item name) and tag (item ID) values
            TextView textViewItem = (TextView) convertView.findViewById(textView);
            textViewItem.setText(objectItem.getDescription());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return convertView;

    }
}
