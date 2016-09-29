
package com.spamish.project.translinkinformer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itinerary implements Parcelable {

    @SerializedName("DurationMins")
    @Expose
    private Integer durationMins;
    @SerializedName("EndTime")
    @Expose
    private String endTime;
    @SerializedName("Fare")
    @Expose
    private Fare fare;
    @SerializedName("FirstDepartureTime")
    @Expose
    private String firstDepartureTime;
    @SerializedName("LastArrivalTime")
    @Expose
    private String lastArrivalTime;
    @SerializedName("Legs")
    @Expose
    private List<Leg> legs = new ArrayList<Leg>();
    @SerializedName("StartTime")
    @Expose
    private String startTime;
    @SerializedName("Transfers")
    @Expose
    private Integer transfers;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Itinerary() {
    }

    /**
     * 
     * @param startTime
     * @param transfers
     * @param fare
     * @param legs
     * @param lastArrivalTime
     * @param durationMins
     * @param endTime
     * @param firstDepartureTime
     */
    public Itinerary(Integer durationMins, String endTime, Fare fare, String firstDepartureTime, String lastArrivalTime, List<Leg> legs, String startTime, Integer transfers) {
        this.durationMins = durationMins;
        this.endTime = endTime;
        this.fare = fare;
        this.firstDepartureTime = firstDepartureTime;
        this.lastArrivalTime = lastArrivalTime;
        this.legs = legs;
        this.startTime = startTime;
        this.transfers = transfers;
    }

    /**
     * 
     * @return
     *     The durationMins
     */
    public Integer getDurationMins() {
        return durationMins;
    }

    /**
     * 
     * @param durationMins
     *     The DurationMins
     */
    public void setDurationMins(Integer durationMins) {
        this.durationMins = durationMins;
    }

    /**
     * 
     * @return
     *     The endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime
     *     The EndTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 
     * @return
     *     The fare
     */
    public Fare getFare() {
        return fare;
    }

    /**
     * 
     * @param fare
     *     The Fare
     */
    public void setFare(Fare fare) {
        this.fare = fare;
    }

    /**
     * 
     * @return
     *     The firstDepartureTime
     */
    public String getFirstDepartureTime() {
        return firstDepartureTime;
    }

    /**
     * 
     * @param firstDepartureTime
     *     The FirstDepartureTime
     */
    public void setFirstDepartureTime(String firstDepartureTime) {
        this.firstDepartureTime = firstDepartureTime;
    }

    /**
     * 
     * @return
     *     The lastArrivalTime
     */
    public String getLastArrivalTime() {
        return lastArrivalTime;
    }

    /**
     * 
     * @param lastArrivalTime
     *     The LastArrivalTime
     */
    public void setLastArrivalTime(String lastArrivalTime) {
        this.lastArrivalTime = lastArrivalTime;
    }

    /**
     * 
     * @return
     *     The legs
     */
    public List<Leg> getLegs() {
        return legs;
    }

    /**
     * 
     * @param legs
     *     The Legs
     */
    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    /**
     * 
     * @return
     *     The startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     *     The StartTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return
     *     The transfers
     */
    public Integer getTransfers() {
        return transfers;
    }

    /**
     * 
     * @param transfers
     *     The Transfers
     */
    public void setTransfers(Integer transfers) {
        this.transfers = transfers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.durationMins);
        dest.writeString(this.endTime);
        dest.writeParcelable(this.fare, flags);
        dest.writeString(this.firstDepartureTime);
        dest.writeString(this.lastArrivalTime);
        dest.writeTypedList(this.legs);
        dest.writeString(this.startTime);
        dest.writeValue(this.transfers);
    }

    protected Itinerary(Parcel in) {
        this.durationMins = (Integer) in.readValue(Integer.class.getClassLoader());
        this.endTime = in.readString();
        this.fare = in.readParcelable(Fare.class.getClassLoader());
        this.firstDepartureTime = in.readString();
        this.lastArrivalTime = in.readString();
        this.legs = in.createTypedArrayList(Leg.CREATOR);
        this.startTime = in.readString();
        this.transfers = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Itinerary> CREATOR = new Parcelable.Creator<Itinerary>() {
        @Override
        public Itinerary createFromParcel(Parcel source) {
            return new Itinerary(source);
        }

        @Override
        public Itinerary[] newArray(int size) {
            return new Itinerary[size];
        }
    };
}
