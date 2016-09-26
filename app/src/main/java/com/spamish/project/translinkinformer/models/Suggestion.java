package com.spamish.project.translinkinformer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Suggestion {

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Type")
    @Expose
    private Integer type;

    /**
     * No args constructor for use in serialization
     *
     */
    public Suggestion() {
    }

    /**
     *
     * @param id
     * @param description
     * @param type
     */
    public Suggestion(String description, String id, Integer type) {
        this.description = description;
        this.id = id;
        this.type = type;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The type
     */
    public Integer getType() {
        return type;
    }

    /**
     *
     * @param type
     * The Type
     */
    public void setType(Integer type) {
        this.type = type;
    }
}
