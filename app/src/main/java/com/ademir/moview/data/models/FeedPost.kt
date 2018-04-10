package com.ademir.moview.model

import android.arch.persistence.room.Entity
import com.ademir.moview.data.models.Movie
import com.squareup.moshi.Json


class FeedPost(@Json(name="user") val user: User,
               @Json(name="movie") val movie: Movie,
               @Json(name="content") val content: String,
               @Json(name="likes") val likes: Int,
               @Json(name="created_at") val createdAt: Long)
