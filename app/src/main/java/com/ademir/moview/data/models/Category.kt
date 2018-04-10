package com.ademir.moview.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(@PrimaryKey val id: String,
                    val name: String)