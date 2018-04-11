package com.ademir.moview.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "movies")
data class Movie(
        @PrimaryKey var id: String = "",
        var adult: Boolean = false,
        var budget: Long = 0L,
        @Ignore var genres: List<Category> = emptyList(), // MANY TO MANY
        var homepage: String = "",
        var overview: String = "",
        var popularity: Double = 0.0,
        var releaseDate: String = "",
        var revenue: Long = 0L,
        var runtime: Int = 0,
        var status: String = "",
        var tagline: String = "",
        var title: String = "",
        var video: Boolean = false,
        var voteAverage: Double = 0.0,
        var voteCount: Int = 0,
        @Json(name = "poster_path") var posterPath: String = "",
        @Json(name = "backdrop_path") var backdropPath: String = ""
) {

    class Payload(@Json(name = "total_results") val count: Int,
                  val results: List<Movie>,
                  val page: Int)

}