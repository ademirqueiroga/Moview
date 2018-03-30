package com.ademir.mooview.rest.payloads

import com.ademir.mooview.model.Movie
import com.ademir.moview.model.User


class SearchPayload(val movies: List<Movie>, val users: List<User>)
