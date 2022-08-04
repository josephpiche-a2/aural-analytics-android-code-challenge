package com.auralanalytics.android.challenge.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This is the primary reddit data object for posts. While the term `Thing` may
 * seem vague, it is the term the Reddit API uses, so it's being continued here
 * for clarity.
 */
@Serializable
data class Thing(
    val id: String,
    val subreddit: String,
    val title: String,
    val score: Int,
    val author: String,
    val created: Double,
    @SerialName("num_comments") val numComments: Int,
    val permalink: String,
    val url: String?,
    val selftext: String?,
    val thumbnail: String?,
    @SerialName("post_hint") val postHint: String?
)
