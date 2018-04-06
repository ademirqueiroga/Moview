package com.ademir.mooview.data.models

import com.squareup.moshi.Json

import java.text.NumberFormat
import java.util.Locale


class Movie {

    var id: Int = 0
    var title: String? = null
    var categories: List<Category>? = null
    var overview: String? = null
    var budget: Int = 0
    var revenue: Int = 0
    var runtime: Int = 0
    @Json(name = "following_comments")
    var followingComments: Int = 0
    var rating: Float = 0.toFloat()
    @Json(name = "release_date")
    var releaseDate: String? = null
    @Json(name = "poster_path")
    var posterPath: String? = null
    @Json(name = "backdrop_path")
    var backdropPath: String? = null


    val year: String
        get() = releaseDate!!.substring(0, 4)

    fun getRating(): String {
        return String.format(Locale.getDefault(), "%.1f", this.rating)
    }

    fun getBudget(): String {
        return NumberFormat.getCurrencyInstance(Locale.US).format(budget.toLong())
    }

    fun getRevenue(): String {
        return NumberFormat.getCurrencyInstance(Locale.US).format(revenue.toLong())
    }

    fun getRuntime(): String? {
        if (runtime > 0) {
            return String.format(Locale.getDefault(), "%02dmin", runtime)
        } else {
            return null
        }
    }

    fun getCategories(): String {
        val sb = StringBuilder()
        for (category in categories!!) {
            sb.append(category.name).append(", ")
        }
        val len = sb.length
        if (len > 1) {
            sb.delete(len - 2, len - 1)
        }
        return sb.toString()
    }

}
