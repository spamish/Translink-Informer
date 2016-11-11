package com.spamish.project.translinkinformer.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nearby {

    @SerializedName("NearbyStops")
    @Expose
    private List<NearbyStop> nearbyStops = new ArrayList<NearbyStop>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Nearby() {
    }

    /**
     *
     * @param nearbyStops
     */
    public Nearby(List<NearbyStop> nearbyStops) {
        this.nearbyStops = nearbyStops;
    }

    /**
     *
     * @return
     * The nearbyStops
     */
    public List<NearbyStop> getNearbyStops() {
        return nearbyStops;
    }

    /**
     *
     * @param nearbyStops
     * The NearbyStops
     */
    public void setNearbyStops(List<NearbyStop> nearbyStops) {
        this.nearbyStops = nearbyStops;
    }

}