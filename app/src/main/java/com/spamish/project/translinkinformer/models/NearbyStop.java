package com.spamish.project.translinkinformer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearbyStop {

    @SerializedName("Distance")
    @Expose
    private Distance distance;
    @SerializedName("StopId")
    @Expose
    private String stopId;

    /**
     * No args constructor for use in serialization
     *
     */
    public NearbyStop() {
    }

    /**
     *
     * @param distance
     * @param stopId
     */
    public NearbyStop(Distance distance, String stopId) {
        this.distance = distance;
        this.stopId = stopId;
    }

    /**
     *
     * @return
     * The distance
     */
    public Distance getDistance() {
        return distance;
    }

    /**
     *
     * @param distance
     * The Distance
     */
    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    /**
     *
     * @return
     * The stopId
     */
    public String getStopId() {
        return stopId;
    }

    /**
     *
     * @param stopId
     * The StopId
     */
    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

}