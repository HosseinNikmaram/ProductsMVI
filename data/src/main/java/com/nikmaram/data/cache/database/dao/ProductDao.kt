package com.nikmaram.data.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nikmaram.data.cache.model.ProductEntity

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProducts(productEntities: List<ProductEntity>)

    @Query("SELECT * FROM products WHERE title LIKE :searchQuery ORDER BY title ASC")
    suspend fun searchProductsByTitle(searchQuery: String): List<ProductEntity>

    @Query("SELECT * FROM products ORDER BY title ASC")
    suspend fun getProducts():List<ProductEntity>

    @Update
    suspend fun updateProduct(product: ProductEntity)
}