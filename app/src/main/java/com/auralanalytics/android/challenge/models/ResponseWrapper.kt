package com.auralanalytics.android.challenge.models

import kotlinx.serialization.Serializable

/**
 * All Reddit data objects are wrapped in this format. This generic class allows
 * for consistent unwrapping of them.
 */
@Serializable
data class ResponseWrapper<T>(
    val kind: String,
    val data: T
)
