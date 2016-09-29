
package com.spamish.project.translinkinformer.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Journeys implements Parcelable {

    @SerializedName("TravelOptions")
    @Expose
    private TravelOptions travelOptions;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Journeys() {
    }

    /**
     * 
     * @param travelOptions
     */
    public Journeys(TravelOptions travelOptions) {
        this.travelOptions = travelOptions;
    }

    /**
     * 
     * @return
     *     The travelOptions
     */
    public TravelOptions getTravelOptions() {
        return travelOptions;
    }

    /**
     * 
     * @param travelOptions
     *     The TravelOptions
     */
    public void setTravelOptions(TravelOptions travelOptions) {
        this.travelOptions = travelOptions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.travelOptions, flags);
    }

    protected Journeys(Parcel in) {
        this.travelOptions = in.readParcelable(TravelOptions.class.getClassLoader());
    }

    public static final Parcelable.Creator<Journeys> CREATOR = new Parcelable.Creator<Journeys>() {
        @Override
        public Journeys createFromParcel(Parcel source) {
            return new Journeys(source);
        }

        @Override
        public Journeys[] newArray(int size) {
            return new Journeys[size];
        }
    };
}
