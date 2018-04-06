package com.ademir.mooview.data.remote.payloads

import com.ademir.mooview.data.models.Movie
import com.ademir.moview.model.User


class SearchPayload(val movies: List<Movie>, val users: List<User>)
