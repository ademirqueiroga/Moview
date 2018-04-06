package com.ademir.mooview.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ademir.mooview.data.models.Category
import com.ademir.mooview.data.models.Movie
import com.ademir.moview.model.FeedPost

/**
 * Created by ademir on 05/04/18.
 */
@Database(
        entities = [Category::class, FeedPost::class, Movie::class],
        version = 1,
        exportSchema = false
)
abstract class MoviewDb : RoomDatabase() {

}