
package com.spamish.project.translinkinformer.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leg implements Parcelable {

    @SerializedName("ArrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("DepartureTime")
    @Expose
    private String departureTime;
    @SerializedName("DistanceM")
    @Expose
    private Integer distanceM;
    @SerializedName("DurationMins")
    @Expose
    private Integer durationMins;
    @SerializedName("FromStopId")
    @Expose
    private String fromStopId;
    @SerializedName("Instruction")
    @Expose
    private String instruction;
    @SerializedName("Polyline")
    @Expose
    private String polyline;
    @SerializedName("Route")
    @Expose
    private Route route;
    @SerializedName("SameVehicleContinuation")
    @Expose
    private Boolean sameVehicleContinuation;
    @SerializedName("ToStopId")
    @Expose
    private String toStopId;
    @SerializedName("TravelMode")
    @Expose
    private String travelMode;
    @SerializedName("TripId")
    @Expose
    private String tripId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Leg() {
    }

    /**
     * 
     * @param arrivalTime
     * @param polyline
     * @param distanceM
     * @param sameVehicleContinuation
     * @param tripId
     * @param route
     * @param departureTime
     * @param instruction
     * @param fromStopId
     * @param durationMins
     * @param toStopId
     * @param travelMode
     */
    public Leg(String arrivalTime, String departureTime, Integer distanceM, Integer durationMins, String fromStopId, String instruction, String polyline, Route route, Boolean sameVehicleContinuation, String toStopId, String travelMode, String tripId) {
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.distanceM = distanceM;
        this.durationMins = durationMins;
        this.fromStopId = fromStopId;
        this.instruction = instruction;
        this.polyline = polyline;
        this.route = route;
        this.sameVehicleContinuation = sameVehicleContinuation;
        this.toStopId = toStopId;
        this.travelMode = travelMode;
        this.tripId = tripId;
    }

    /**
     * 
     * @return
     *     The arrivalTime
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * 
     * @param arrivalTime
     *     The ArrivalTime
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * 
     * @return
     *     The departureTime
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * 
     * @param departureTime
     *     The DepartureTime
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * 
     * @return
     *     The distanceM
     */
    public Integer getDistanceM() {
        return distanceM;
    }

    /**
     * 
     * @param distanceM
     *     The DistanceM
     */
    public void setDistanceM(Integer distanceM) {
        this.distanceM = distanceM;
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
     *     The fromStopId
     */
    public String getFromStopId() {
        return fromStopId;
    }

    /**
     * 
     * @param fromStopId
     *     The FromStopId
     */
    public void setFromStopId(String fromStopId) {
        this.fromStopId = fromStopId;
    }

    /**
     * 
     * @return
     *     The instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * 
     * @param instruction
     *     The Instruction
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * 
     * @return
     *     The polyline
     */
    public String getPolyline() {
        return polyline;
    }

    /**
     * 
     * @param polyline
     *     The Polyline
     */
    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    /**
     * 
     * @return
     *     The route
     */
    public Route getRoute() {
        return route;
    }

    /**
     * 
     * @param route
     *     The Route
     */
    public void setRoute(Route route) {
        this.route = route;
    }

    /**
     * 
     * @return
     *     The sameVehicleContinuation
     */
    public Boolean getSameVehicleContinuation() {
        return sameVehicleContinuation;
    }

    /**
     * 
     * @param sameVehicleContinuation
     *     The SameVehicleContinuation
     */
    public void setSameVehicleContinuation(Boolean sameVehicleContinuation) {
        this.sameVehicleContinuation = sameVehicleContinuation;
    }

    /**
     * 
     * @return
     *     The toStopId
     */
    public String getToStopId() {
        return toStopId;
    }

    /**
     * 
     * @param toStopId
     *     The ToStopId
     */
    public void setToStopId(String toStopId) {
        this.toStopId = toStopId;
    }

    /**
     * 
     * @return
     *     The travelMode
     */
    public String getTravelMode() {
        return travelMode;
    }

    /**
     * 
     * @param travelMode
     *     The TravelMode
     */
    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    /**
     * 
     * @return
     *     The tripId
     */
    public String getTripId() {
        return tripId;
    }

    /**
     * 
     * @param tripId
     *     The TripId
     */
    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.arrivalTime);
        dest.writeString(this.departureTime);
        dest.writeValue(this.distanceM);
        dest.writeValue(this.durationMins);
        dest.writeString(this.fromStopId);
        dest.writeString(this.instruction);
        dest.writeString(this.polyline);
        dest.writeParcelable(this.route, flags);
        dest.writeValue(this.sameVehicleContinuation);
        dest.writeString(this.toStopId);
        dest.writeString(this.travelMode);
        dest.writeString(this.tripId);
    }

    protected Leg(Parcel in) {
        this.arrivalTime = in.readString();
        this.departureTime = in.readString();
        this.distanceM = (Integer) in.readValue(Integer.class.getClassLoader());
        this.durationMins = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fromStopId = in.readString();
        this.instruction = in.readString();
        this.polyline = in.readString();
        this.route = in.readParcelable(Route.class.getClassLoader());
        this.sameVehicleContinuation = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.toStopId = in.readString();
        this.travelMode = in.readString();
        this.tripId = in.readString();
    }

    public static final Parcelable.Creator<Leg> CREATOR = new Parcelable.Creator<Leg>() {
        @Override
        public Leg createFromParcel(Parcel source) {
            return new Leg(source);
        }

        @Override
        public Leg[] newArray(int size) {
            return new Leg[size];
        }
    };
}
