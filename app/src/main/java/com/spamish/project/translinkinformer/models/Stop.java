
package com.spamish.project.translinkinformer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stop implements Parcelable {

    @SerializedName("HasParentLocation")
    @Expose
    private Boolean hasParentLocation;
    @SerializedName("Intersection1")
    @Expose
    private String intersection1;
    @SerializedName("Intersection2")
    @Expose
    private String intersection2;
    @SerializedName("LocationDescription")
    @Expose
    private String locationDescription;
    @SerializedName("ParentLocation")
    @Expose
    private ParentLocation parentLocation;
    @SerializedName("Routes")
    @Expose
    private List<Route> routes = new ArrayList<Route>();
    @SerializedName("ServiceType")
    @Expose
    private String serviceType;
    @SerializedName("StopId")
    @Expose
    private String stopId;
    @SerializedName("StopNoteCodes")
    @Expose
    private String stopNoteCodes;
    @SerializedName("Street")
    @Expose
    private String street;
    @SerializedName("Suburb")
    @Expose
    private String suburb;
    @SerializedName("Zone")
    @Expose
    private String zone;
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
     *     The hasParentLocation
     */
    public Boolean getHasParentLocation() {
        return hasParentLocation;
    }

    /**
     * 
     * @param hasParentLocation
     *     The HasParentLocation
     */
    public void setHasParentLocation(Boolean hasParentLocation) {
        this.hasParentLocation = hasParentLocation;
    }

    /**
     * 
     * @return
     *     The intersection1
     */
    public String getIntersection1() {
        return intersection1;
    }

    /**
     * 
     * @param intersection1
     *     The Intersection1
     */
    public void setIntersection1(String intersection1) {
        this.intersection1 = intersection1;
    }

    /**
     * 
     * @return
     *     The intersection2
     */
    public String getIntersection2() {
        return intersection2;
    }

    /**
     * 
     * @param intersection2
     *     The Intersection2
     */
    public void setIntersection2(String intersection2) {
        this.intersection2 = intersection2;
    }

    /**
     * 
     * @return
     *     The locationDescription
     */
    public String getLocationDescription() {
        return locationDescription;
    }

    /**
     * 
     * @param locationDescription
     *     The LocationDescription
     */
    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    /**
     * 
     * @return
     *     The parentLocation
     */
    public ParentLocation getParentLocation() {
        return parentLocation;
    }

    /**
     * 
     * @param parentLocation
     *     The ParentLocation
     */
    public void setParentLocation(ParentLocation parentLocation) {
        this.parentLocation = parentLocation;
    }

    /**
     * 
     * @return
     *     The routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * 
     * @param routes
     *     The Routes
     */
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    /**
     * 
     * @return
     *     The serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * 
     * @param serviceType
     *     The ServiceType
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * 
     * @return
     *     The stopId
     */
    public String getStopId() {
        return stopId;
    }

    /**
     * 
     * @param stopId
     *     The StopId
     */
    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    /**
     * 
     * @return
     *     The stopNoteCodes
     */
    public String getStopNoteCodes() {
        return stopNoteCodes;
    }

    /**
     * 
     * @param stopNoteCodes
     *     The StopNoteCodes
     */
    public void setStopNoteCodes(String stopNoteCodes) {
        this.stopNoteCodes = stopNoteCodes;
    }

    /**
     * 
     * @return
     *     The street
     */
    public String getStreet() {
        return street;
    }

    /**
     * 
     * @param street
     *     The Street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 
     * @return
     *     The suburb
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * 
     * @param suburb
     *     The Suburb
     */
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    /**
     * 
     * @return
     *     The zone
     */
    public String getZone() {
        return zone;
    }

    /**
     * 
     * @param zone
     *     The Zone
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

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
        dest.writeValue(this.hasParentLocation);
        dest.writeString(this.intersection1);
        dest.writeString(this.intersection2);
        dest.writeString(this.locationDescription);
        dest.writeParcelable(this.parentLocation, flags);
        dest.writeTypedList(this.routes);
        dest.writeString(this.serviceType);
        dest.writeString(this.stopId);
        dest.writeString(this.stopNoteCodes);
        dest.writeString(this.street);
        dest.writeString(this.suburb);
        dest.writeString(this.zone);
        dest.writeString(this.description);
        dest.writeString(this.id);
        dest.writeString(this.landmarkType);
        dest.writeParcelable(this.position, flags);
        dest.writeString(this.type);
    }

    public Stop() {
    }

    protected Stop(Parcel in) {
        this.hasParentLocation = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.intersection1 = in.readString();
        this.intersection2 = in.readString();
        this.locationDescription = in.readString();
        this.parentLocation = in.readParcelable(ParentLocation.class.getClassLoader());
        this.routes = in.createTypedArrayList(Route.CREATOR);
        this.serviceType = in.readString();
        this.stopId = in.readString();
        this.stopNoteCodes = in.readString();
        this.street = in.readString();
        this.suburb = in.readString();
        this.zone = in.readString();
        this.description = in.readString();
        this.id = in.readString();
        this.landmarkType = in.readString();
        this.position = in.readParcelable(Position.class.getClassLoader());
        this.type = in.readString();
    }

    public static final Parcelable.Creator<Stop> CREATOR = new Parcelable.Creator<Stop>() {
        @Override
        public Stop createFromParcel(Parcel source) {
            return new Stop(source);
        }

        @Override
        public Stop[] newArray(int size) {
            return new Stop[size];
        }
    };
}
