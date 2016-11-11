package com.spamish.project.translinkinformer.api_tools;

import com.spamish.project.translinkinformer.models.Journeys;
import com.spamish.project.translinkinformer.models.Nearby;
import com.spamish.project.translinkinformer.models.Routes;
import com.spamish.project.translinkinformer.models.Stops;
import com.spamish.project.translinkinformer.models.Suggested;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface TranslinkAPI {

    @GET("location/rest/suggest")
    Observable<Suggested> getSuggest(
            @Query("input") String input,
            @Query("filter") String filter,
            @Query("maxResults") int maxResults
    );

    @GET("travel/rest/plan/{fromLocationId}/{toLocationId}")
    Observable<Journeys> getPlan(
            @Path("fromLocationId") String startLoc,
            @Path("toLocationId") String destLoc,
            @Query("timeMode") String timeMode,
            @Query("at") String date,
            @Query("vehicleTypes") String vehicleTypes,
            @Query("walkSpeed") String walkSpeed,
            @Query("maximumWalkingDistanceM") int maxDist,
            @Query("serviceTypes") String serviceTypes,
            @Query("fareTypes") String fareTypes
    );

    @GET("location/rest/stops")
    Observable<Stops> getStopList(
            @Query("ids") String ids
    );

    @GET("network/rest/routes")
    Observable<Routes> getRoutes(
            @Query("date") String date
    );

    @GET("location/rest/stops-nearby/{locationId}")
    Observable<Nearby> getNearby(
            @Path("locationId") String locationId,
            @Query("radiusM") int radius,
            @Query("useWalkingDistance") boolean distance,
            @Query("maxResults") int maxResults
    );
}
