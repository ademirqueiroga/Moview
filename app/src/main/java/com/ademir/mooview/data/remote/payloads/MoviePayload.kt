package com.ademir.mooview.data.remote.payloads

import com.ademir.mooview.data.models.Movie
import com.squareup.moshi.Json


class MoviePayload(val count: Int,
                   @Json(name="results") val movies: List<Movie>)
