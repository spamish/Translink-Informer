
package com.spamish.project.translinkinformer.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fare_ implements Parcelable {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Price")
    @Expose
    private String price;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Fare_() {
    }

    /**
     * 
     * @param price
     * @param name
     */
    public Fare_(String name, String price) {
        this.name = name;
        this.price = price;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The price
     */
    public String getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The Price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.price);
    }

    protected Fare_(Parcel in) {
        this.name = in.readString();
        this.price = in.readString();
    }

    public static final Parcelable.Creator<Fare_> CREATOR = new Parcelable.Creator<Fare_>() {
        @Override
        public Fare_ createFromParcel(Parcel source) {
            return new Fare_(source);
        }

        @Override
        public Fare_[] newArray(int size) {
            return new Fare_[size];
        }
    };
}
