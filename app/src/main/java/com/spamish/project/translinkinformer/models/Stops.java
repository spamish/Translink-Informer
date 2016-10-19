
package com.spamish.project.translinkinformer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stops implements Parcelable {

    @SerializedName("Stops")
    @Expose
    private List<Stop> stops = new ArrayList<>();

    public Stops() {
    }

    public Stops(List<Stop> stops) {
        this.stops = stops;
    }

    /**
     * 
     * @return
     *     The stops
     */
    public List<Stop> getStops() {
        return stops;
    }

    /**
     * 
     * @param stops
     *     The Stops
     */
    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.stops);
    }

    protected Stops(Parcel in) {
        this.stops = new ArrayList<Stop>();
        in.readList(this.stops, Stop.class.getClassLoader());
    }

    public static final Parcelable.Creator<Stops> CREATOR = new Parcelable.Creator<Stops>() {
        @Override
        public Stops createFromParcel(Parcel source) {
            return new Stops(source);
        }

        @Override
        public Stops[] newArray(int size) {
            return new Stops[size];
        }
    };
}
