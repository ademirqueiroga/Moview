package com.ademir.moview.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.ademir.moview.commons.createParcel
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
        @Json(name = "release_date") var releaseDate: String = "",
        var revenue: Long = 0L,
        var runtime: Int = 0,
        var status: String = "",
        var tagline: String = "",
        var title: String = "",
        var video: Boolean = false,
        @Json(name = "vote_average") var voteAverage: Double = 0.0,
        @Json(name = "vote_count") var voteCount: Int = 0,
        @Json(name = "poster_path") var posterPath: String = "",
        @Json(name = "backdrop_path") var backdropPath: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readLong(),
            emptyList(), // TODO genres
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeLong(budget)
        // TODO add genres value
        parcel.writeString(homepage)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(releaseDate)
        parcel.writeLong(revenue)
        parcel.writeInt(runtime)
        parcel.writeString(status)
        parcel.writeString(tagline)
        parcel.writeString(title)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(voteAverage)
        parcel.writeInt(voteCount)
        parcel.writeString(posterPath)
        parcel.writeString(backdropPath)
    }

    override fun describeContents() = 0

    class Payload(@Json(name = "total_results") val count: Int,
                  val results: List<Movie>,
                  val page: Int)

    companion object {

        const val EXTRA_MOVIE = "extra_movie"

        @JvmField
        val CREATOR = createParcel { Movie(it) }

    }

}