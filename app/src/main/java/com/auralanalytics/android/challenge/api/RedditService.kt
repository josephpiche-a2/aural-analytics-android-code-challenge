package com.auralanalytics.android.challenge.api

import com.auralanalytics.android.challenge.models.Listing
import com.auralanalytics.android.challenge.models.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    @GET("/r/{subreddit}/{sort}.json")
    suspend fun getListing(
        @Path("subreddit") subreddit: String,
        @Path("sort") sort: String = "hot",
        @Query("limit") limit: Int = 25,
        @Query("before") before: String? = null,
        @Query("after") after: String? = null
    ): ResponseWrapper<Listing>
}