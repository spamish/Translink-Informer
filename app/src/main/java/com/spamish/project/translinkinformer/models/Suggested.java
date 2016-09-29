
package com.spamish.project.translinkinformer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Suggested implements Parcelable {

    @SerializedName("Suggestions")
    @Expose
    private List<Suggestion> suggestions = new ArrayList<>();

    /**
     * 
     * @return
     *     The suggestions
     */
    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    /**
     *
     * @param suggestions
     *     The Suggestions
     */
    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.suggestions);
    }

    public Suggested() {
    }

    protected Suggested(Parcel in) {
        this.suggestions = in.createTypedArrayList(Suggestion.CREATOR);
    }

    public static final Parcelable.Creator<Suggested> CREATOR = new Parcelable.Creator<Suggested>() {
        @Override
        public Suggested createFromParcel(Parcel source) {
            return new Suggested(source);
        }

        @Override
        public Suggested[] newArray(int size) {
            return new Suggested[size];
        }
    };
}
