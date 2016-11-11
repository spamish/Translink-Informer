
package com.spamish.project.translinkinformer.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("place_type")
    @Expose
    private String placeType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("contained_within")
    @Expose
    private List<Object> containedWithin = new ArrayList<>();
    @SerializedName("bounding_box")
    @Expose
    private BoundingBox boundingBox;
    @SerializedName("attributes")
    @Expose
    private Object attributes;

    /**
     * No args constructor for use in serialization
     *
     */
    public Place() {
    }

    /**
     *
     * @param id
     * @param boundingBox
     * @param containedWithin
     * @param name
     * @param countryCode
     * @param attributes
     * @param fullName
     * @param placeType
     * @param url
     * @param country
     */
    public Place(String id, String url, String placeType, String name, String fullName, String countryCode, String country, List<Object> containedWithin, BoundingBox boundingBox, Object attributes) {
        this.id = id;
        this.url = url;
        this.placeType = placeType;
        this.name = name;
        this.fullName = fullName;
        this.countryCode = countryCode;
        this.country = country;
        this.containedWithin = containedWithin;
        this.boundingBox = boundingBox;
        this.attributes = attributes;
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
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     *     The placeType
     */
    public String getPlaceType() {
        return placeType;
    }

    /**
     *
     * @param placeType
     *     The place_type
     */
    public void setPlaceType(String placeType) {
        this.placeType = placeType;
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
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     *     The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *
     * @return
     *     The countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     *
     * @param countryCode
     *     The country_code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     *
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     *     The containedWithin
     */
    public List<Object> getContainedWithin() {
        return containedWithin;
    }

    /**
     *
     * @param containedWithin
     *     The contained_within
     */
    public void setContainedWithin(List<Object> containedWithin) {
        this.containedWithin = containedWithin;
    }

    /**
     *
     * @return
     *     The boundingBox
     */
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    /**
     *
     * @param boundingBox
     *     The bounding_box
     */
    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     *
     * @return
     *     The attributes
     */
    public Object getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

}
