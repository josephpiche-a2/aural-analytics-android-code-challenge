package com.auralanalytics.android.challenge.models

import kotlinx.serialization.Serializable

/**
 * This class represents the set of results from the Reddit API for a generic
 * set of `Things`. In this app this will specifically be a list of posts in a
 * subreddit, but this object is used generically in the Reddit API for other
 * collections of Things.
 */
@Serializable
data class Listing(
    val modhash: String,

    // this is the `id` on a `Thing` which can be used to retrieve the previous
    // page of content
    val before: String?,

    // this is the `id` on a `Thing` which can be used to retrieve the following
    // page of content
    val after: String?,

    val children: List<ResponseWrapper<Thing>>
)
