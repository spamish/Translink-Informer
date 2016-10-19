
package com.spamish.project.translinkinformer.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParentLocation implements Parcelable {

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("LandmarkType")
    @Expose
    private String landmarkType;
    @SerializedName("Position")
    @Expose
    private Position position;
    @SerializedName("Type")
    @Expose
    private String type;

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The landmarkType
     */
    public String getLandmarkType() {
        return landmarkType;
    }

    /**
     * 
     * @param landmarkType
     *     The LandmarkType
     */
    public void setLandmarkType(String landmarkType) {
        this.landmarkType = landmarkType;
    }

    /**
     * 
     * @return
     *     The position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * 
     * @param position
     *     The Position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The Type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.id);
        dest.writeString(this.landmarkType);
        dest.writeParcelable(this.position, flags);
        dest.writeString(this.type);
    }

    public ParentLocation() {
    }

    protected ParentLocation(Parcel in) {
        this.description = in.readString();
        this.id = in.readString();
        this.landmarkType = in.readString();
        this.position = in.readParcelable(Position.class.getClassLoader());
        this.type = in.readString();
    }

    public static final Parcelable.Creator<ParentLocation> CREATOR = new Parcelable.Creator<ParentLocation>() {
        @Override
        public ParentLocation createFromParcel(Parcel source) {
            return new ParentLocation(source);
        }

        @Override
        public ParentLocation[] newArray(int size) {
            return new ParentLocation[size];
        }
    };
}
