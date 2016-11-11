package com.spamish.project.translinkinformer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distance {

    @SerializedName("DistanceM")
    @Expose
    private String distanceM;
    @SerializedName("IsApproximate")
    @Expose
    private Boolean isApproximate;

    /**
     * No args constructor for use in serialization
     *
     */
    public Distance() {
    }

    /**
     *
     * @param distanceM
     * @param isApproximate
     */
    public Distance(String distanceM, Boolean isApproximate) {
        this.distanceM = distanceM;
        this.isApproximate = isApproximate;
    }

    /**
     *
     * @return
     * The distanceM
     */
    public String getDistanceM() {
        return distanceM;
    }

    /**
     *
     * @param distanceM
     * The DistanceM
     */
    public void setDistanceM(String distanceM) {
        this.distanceM = distanceM;
    }

    /**
     *
     * @return
     * The isApproximate
     */
    public Boolean getIsApproximate() {
        return isApproximate;
    }

    /**
     *
     * @param isApproximate
     * The IsApproximate
     */
    public void setIsApproximate(Boolean isApproximate) {
        this.isApproximate = isApproximate;
    }

}