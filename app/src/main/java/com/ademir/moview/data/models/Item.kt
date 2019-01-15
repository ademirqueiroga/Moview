package com.ademir.moview.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
class Item(

    @PrimaryKey
    @Json(name = "id")
    var id: Long = 0L,

    @Json(name = "adult")
    var adult: Boolean = false,

    @Json(name = "backdrop_path")
    var backdropPath: String = "",

    @Json(name = "genre_ids")
    var genres: List<Long> = emptyList(),

    @Json(name = "original_language")
    var originalLanguage: String = "",

    @Json(name = "original_title")
    var originalTitle: String = "",

    @Json(name = "overview")
    var overview: String = "",

    @Json(name = "poster_path")
    var posterPath: String = "",

    @Json(name = "release_date")
    var releaseDate: String = "",

    @Json(name = "title")
    var title: String = "",

    @Json(name = "video")
    var video: Boolean = false,

    @Json(name = "vote_average")
    var voteAverage: Double = 0.0,

    @Json(name = "vote_count")
    var voteCount: Int = 0,

    @Json(name = "popularity")
    var popularity: Double

)