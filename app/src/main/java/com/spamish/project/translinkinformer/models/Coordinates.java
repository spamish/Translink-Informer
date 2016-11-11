
package com.spamish.project.translinkinformer.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.Twitter;

public class Coordinates {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("coordinates")
    @Expose
    private List<Double> coordinates = new ArrayList<Double>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Coordinates() {
    }

    /**
     *
     * @param type
     * @param coordinates
     */
    public Coordinates(String type, List<Double> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    /**
     *
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    public Double getLon() {
        return coordinates.get(0);
    }

    public Double getLat() {
        return coordinates.get(1);
    }

    /**
     *
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     *     The coordinates
     */
    public List<Double> getCoordinates() {
        return coordinates;
    }

    /**
     *
     * @param coordinates
     *     The coordinates
     */
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

}
