package com.ademir.moview.data.remote

import com.squareup.moshi.Json

data class RequestPayload<T>(
    val results: List<T>,
    val page: Int,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int
)