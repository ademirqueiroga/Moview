package com.ademir.moview.data.local.category

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.ademir.moview.data.local.BaseDao
import com.ademir.moview.data.models.Category

/**
 * Created by ademir on 05/04/18.
 */
@Dao
interface CategoryDao : BaseDao<Category> {

    @Query("SELECT * FROM categories")
    fun all(): LiveData<List<Category>>

    @Query("DELETE FROM categories")
    fun dropTable()

}