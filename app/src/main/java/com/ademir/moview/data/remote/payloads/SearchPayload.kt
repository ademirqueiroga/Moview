package com.ademir.moview.data.remote.payloads

import com.ademir.moview.data.models.Movie
import com.ademir.moview.model.User


class SearchPayload(val movies: List<Movie>, val users: List<User>)
