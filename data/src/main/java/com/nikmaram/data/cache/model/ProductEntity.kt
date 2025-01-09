package com.nikmaram.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val bookMarked:Boolean = false
)