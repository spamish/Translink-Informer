
package com.spamish.project.translinkinformer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fare implements Parcelable {

    @SerializedName("Fares")
    @Expose
    private List<Fare_> fares = new ArrayList<Fare_>();
    @SerializedName("MaximumZone")
    @Expose
    private Integer maximumZone;
    @SerializedName("MinimumZone")
    @Expose
    private Integer minimumZone;
    @SerializedName("TotalZones")
    @Expose
    private Integer totalZones;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Fare() {
    }

    /**
     * 
     * @param fares
     * @param totalZones
     * @param minimumZone
     * @param maximumZone
     */
    public Fare(List<Fare_> fares, Integer maximumZone, Integer minimumZone, Integer totalZones) {
        this.fares = fares;
        this.maximumZone = maximumZone;
        this.minimumZone = minimumZone;
        this.totalZones = totalZones;
    }

    /**
     * 
     * @return
     *     The fares
     */
    public List<Fare_> getFares() {
        return fares;
    }

    /**
     * 
     * @param fares
     *     The Fares
     */
    public void setFares(List<Fare_> fares) {
        this.fares = fares;
    }

    /**
     * 
     * @return
     *     The maximumZone
     */
    public Integer getMaximumZone() {
        return maximumZone;
    }

    /**
     * 
     * @param maximumZone
     *     The MaximumZone
     */
    public void setMaximumZone(Integer maximumZone) {
        this.maximumZone = maximumZone;
    }

    /**
     * 
     * @return
     *     The minimumZone
     */
    public Integer getMinimumZone() {
        return minimumZone;
    }

    /**
     * 
     * @param minimumZone
     *     The MinimumZone
     */
    public void setMinimumZone(Integer minimumZone) {
        this.minimumZone = minimumZone;
    }

    /**
     * 
     * @return
     *     The totalZones
     */
    public Integer getTotalZones() {
        return totalZones;
    }

    /**
     * 
     * @param totalZones
     *     The TotalZones
     */
    public void setTotalZones(Integer totalZones) {
        this.totalZones = totalZones;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.fares);
        dest.writeValue(this.maximumZone);
        dest.writeValue(this.minimumZone);
        dest.writeValue(this.totalZones);
    }

    protected Fare(Parcel in) {
        this.fares = new ArrayList<Fare_>();
        in.readList(this.fares, Fare_.class.getClassLoader());
        this.maximumZone = (Integer) in.readValue(Integer.class.getClassLoader());
        this.minimumZone = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalZones = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Fare> CREATOR = new Parcelable.Creator<Fare>() {
        @Override
        public Fare createFromParcel(Parcel source) {
            return new Fare(source);
        }

        @Override
        public Fare[] newArray(int size) {
            return new Fare[size];
        }
    };
}
