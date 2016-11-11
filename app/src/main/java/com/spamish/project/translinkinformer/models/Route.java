package com.spamish.project.translinkinformer.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route implements Parcelable {

    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Direction")
    @Expose
    private Integer direction;
    @SerializedName("IsExpress")
    @Expose
    private Boolean isExpress;
    @SerializedName("IsFree")
    @Expose
    private Boolean isFree;
    @SerializedName("IsPrepaid")
    @Expose
    private Boolean isPrepaid;
    @SerializedName("IsTransLinkService")
    @Expose
    private Boolean isTransLinkService;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("RegionId")
    @Expose
    private String regionId;
    @SerializedName("RouteId")
    @Expose
    private String routeId;
    @SerializedName("ServiceType")
    @Expose
    private Integer serviceType;
    @SerializedName("Vehicle")
    @Expose
    private Integer vehicle;

    /**
     * No args constructor for use in serialization
     *
     */
    public Route() {
    }

    /**
     *
     * @param isTransLinkService
     * @param isFree
     * @param isExpress
     * @param direction
     * @param vehicle
     * @param name
     * @param routeId
     * @param code
     * @param serviceType
     * @param isPrepaid
     * @param regionId
     */
    public Route(String code, Integer direction, Boolean isExpress, Boolean isFree, Boolean isPrepaid, Boolean isTransLinkService, String name, String regionId, String routeId, Integer serviceType, Integer vehicle) {
        this.code = code;
        this.direction = direction;
        this.isExpress = isExpress;
        this.isFree = isFree;
        this.isPrepaid = isPrepaid;
        this.isTransLinkService = isTransLinkService;
        this.name = name;
        this.regionId = regionId;
        this.routeId = routeId;
        this.serviceType = serviceType;
        this.vehicle = vehicle;
    }

    /**
     *
     * @return
     * The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The Code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The direction
     */
    public Integer getDirection() {
        return direction;
    }

    /**
     *
     * @param direction
     * The Direction
     */
    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    /**
     *
     * @return
     * The isExpress
     */
    public Boolean getIsExpress() {
        return isExpress;
    }

    /**
     *
     * @param isExpress
     * The IsExpress
     */
    public void setIsExpress(Boolean isExpress) {
        this.isExpress = isExpress;
    }

    /**
     *
     * @return
     * The isFree
     */
    public Boolean getIsFree() {
        return isFree;
    }

    /**
     *
     * @param isFree
     * The IsFree
     */
    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    /**
     *
     * @return
     * The isPrepaid
     */
    public Boolean getIsPrepaid() {
        return isPrepaid;
    }

    /**
     *
     * @param isPrepaid
     * The IsPrepaid
     */
    public void setIsPrepaid(Boolean isPrepaid) {
        this.isPrepaid = isPrepaid;
    }

    /**
     *
     * @return
     * The isTransLinkService
     */
    public Boolean getIsTransLinkService() {
        return isTransLinkService;
    }

    /**
     *
     * @param isTransLinkService
     * The IsTransLinkService
     */
    public void setIsTransLinkService(Boolean isTransLinkService) {
        this.isTransLinkService = isTransLinkService;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The regionId
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     *
     * @param regionId
     * The RegionId
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    /**
     *
     * @return
     * The routeId
     */
    public String getRouteId() {
        return routeId;
    }

    /**
     *
     * @param routeId
     * The RouteId
     */
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    /**
     *
     * @return
     * The serviceType
     */
    public Integer getServiceType() {
        return serviceType;
    }

    /**
     *
     * @param serviceType
     * The ServiceType
     */
    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    /**
     *
     * @return
     * The vehicle
     */
    public Integer getVehicle() {
        return vehicle;
    }

    /**
     *
     * @param vehicle
     * The Vehicle
     */
    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeValue(this.direction);
        dest.writeValue(this.isExpress);
        dest.writeValue(this.isFree);
        dest.writeValue(this.isPrepaid);
        dest.writeValue(this.isTransLinkService);
        dest.writeString(this.name);
        dest.writeString(this.regionId);
        dest.writeString(this.routeId);
        dest.writeValue(this.serviceType);
        dest.writeValue(this.vehicle);
    }

    protected Route(android.os.Parcel in) {
        this.code = in.readString();
        this.direction = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isExpress = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isFree = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isPrepaid = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isTransLinkService = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.name = in.readString();
        this.regionId = in.readString();
        this.routeId = in.readString();
        this.serviceType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.vehicle = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Route> CREATOR = new Parcelable.Creator<Route>() {
        @Override
        public Route createFromParcel(android.os.Parcel source) {
            return new Route(source);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };
}