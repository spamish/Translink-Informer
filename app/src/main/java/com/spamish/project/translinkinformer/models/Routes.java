
package com.spamish.project.translinkinformer.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Routes {

    @SerializedName("Routes")
    @Expose
    private List<Route> routes = new ArrayList<Route>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Routes() {
    }

    /**
     *
     * @param routes
     */
    public Routes(List<Route> routes) {
        this.routes = routes;
    }

    /**
     *
     * @return
     * The routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     *
     * @param routes
     * The Routes
     */
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

}