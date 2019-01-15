package com.ademir.moview.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.ademir.moview.data.local.movie.MovieDao
import com.ademir.moview.data.models.Category
import com.ademir.moview.data.models.Movie
import javax.inject.Singleton

/**
 * Created by ademir on 05/04/18.
 */
@Database(
        entities = [Category::class, Movie::class],
        version = 3,
        exportSchema = false
)
abstract class MoviewDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        fun create(context: Context): MoviewDb {
            return Room
                    .databaseBuilder(context.applicationContext, MoviewDb::class.java, "moview.db")
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

}