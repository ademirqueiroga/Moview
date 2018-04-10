package com.ademir.moview.data.local

import android.arch.persistence.room.*

@Dao
interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item: T)

    @Delete
    fun delete(item: T)

    @Update
    fun update(item: T)


}