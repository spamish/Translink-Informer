package com.spamish.project.translinkinformer.opia;

import com.spamish.project.translinkinformer.models.Suggested;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface LocationAPI {

    @GET("location/rest/suggest")
    Observable<Suggested> getSuggest(
            @Query("input") String input,
            @Query("filter") String filter,
            @Query("maxResults") int maxResults);

}
