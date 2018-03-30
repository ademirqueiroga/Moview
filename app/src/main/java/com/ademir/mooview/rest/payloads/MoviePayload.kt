package com.ademir.mooview.rest.payloads

import com.ademir.mooview.model.Movie
import com.squareup.moshi.Json


class MoviePayload(val count: Int,
                   @Json(name="results") val movies: List<Movie>)
