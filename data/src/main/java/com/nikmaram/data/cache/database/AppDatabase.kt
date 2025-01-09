package com.nikmaram.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikmaram.data.cache.database.dao.ProductDao
import com.nikmaram.data.cache.model.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}