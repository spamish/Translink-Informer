
package com.spamish.project.translinkinformer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelOptions implements Parcelable {

    @SerializedName("Itineraries")
    @Expose
    private List<Itinerary> itineraries = new ArrayList<Itinerary>();
    @SerializedName("SearchIsInFlexiLinkArea")
    @Expose
    private Boolean searchIsInFlexiLinkArea;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TravelOptions() {
    }

    /**
     * 
     * @param itineraries
     * @param searchIsInFlexiLinkArea
     */
    public TravelOptions(List<Itinerary> itineraries, Boolean searchIsInFlexiLinkArea) {
        this.itineraries = itineraries;
        this.searchIsInFlexiLinkArea = searchIsInFlexiLinkArea;
    }

    /**
     * 
     * @return
     *     The itineraries
     */
    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    /**
     * 
     * @param itineraries
     *     The Itineraries
     */
    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    /**
     * 
     * @return
     *     The searchIsInFlexiLinkArea
     */
    public Boolean getSearchIsInFlexiLinkArea() {
        return searchIsInFlexiLinkArea;
    }

    /**
     * 
     * @param searchIsInFlexiLinkArea
     *     The SearchIsInFlexiLinkArea
     */
    public void setSearchIsInFlexiLinkArea(Boolean searchIsInFlexiLinkArea) {
        this.searchIsInFlexiLinkArea = searchIsInFlexiLinkArea;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.itineraries);
        dest.writeValue(this.searchIsInFlexiLinkArea);
    }

    protected TravelOptions(Parcel in) {
        this.itineraries = new ArrayList<Itinerary>();
        in.readList(this.itineraries, Itinerary.class.getClassLoader());
        this.searchIsInFlexiLinkArea = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TravelOptions> CREATOR = new Parcelable.Creator<TravelOptions>() {
        @Override
        public TravelOptions createFromParcel(Parcel source) {
            return new TravelOptions(source);
        }

        @Override
        public TravelOptions[] newArray(int size) {
            return new TravelOptions[size];
        }
    };
}
