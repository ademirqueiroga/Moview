package com.ademir.moview.data.local.movie

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.ademir.moview.data.local.BaseDao
import com.ademir.moview.data.models.Movie
import javax.inject.Singleton

/**
 * Created by ademir on 05/04/18.
 */
@Dao
interface MovieDao : BaseDao<Movie> {

    @Query("SELECT * FROM movies")
    fun movies(): DataSource.Factory<Int, Movie>

    @Query("DELETE FROM movies")
    fun dropTable()

    @Query("SELECT count(*) FROM movies")
    fun count(): Int

}