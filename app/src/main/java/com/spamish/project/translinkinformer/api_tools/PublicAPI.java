package com.spamish.project.translinkinformer.api_tools;

import com.spamish.project.translinkinformer.models.Tweet;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface PublicAPI {

    @GET("statuses/user_timeline.json")
    Observable<List<Tweet>> getTimeline(
            @Query("user_id") String user_id,
            @Query("screen_name") String screen_name,
            @Query("since_id") String since_id,
            @Query("trim_user") boolean trim_user,
            @Query("exclude_replies") boolean exclude_replies,
            @Query("include_rts") boolean include_rts
    );
}
