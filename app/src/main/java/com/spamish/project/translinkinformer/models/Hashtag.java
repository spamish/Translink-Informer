
package com.spamish.project.translinkinformer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Hashtag {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("indices")
    @Expose
    private List<Integer> indices = new ArrayList<Integer>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Hashtag() {
    }

    /**
     * 
     * @param text
     * @param indices
     */
    public Hashtag(String text, List<Integer> indices) {
        this.text = text;
        this.indices = indices;
    }

    /**
     * 
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     * @return
     *     The indices
     */
    public List<Integer> getIndices() {
        return indices;
    }

    /**
     * 
     * @param indices
     *     The indices
     */
    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }
}
