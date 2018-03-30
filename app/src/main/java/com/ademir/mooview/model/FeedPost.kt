package com.ademir.moview.model

import com.ademir.mooview.model.Movie
import com.squareup.moshi.Json


class FeedPost(@Json(name="user") val user: User,
               @Json(name="movie") val movie: Movie,
               @Json(name="content") val content: String,
               @Json(name="likes") val likes: Int,
               @Json(name="created_at") val createdAt: Long)
