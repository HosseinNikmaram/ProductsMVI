package com.nikmaram.data.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikmaram.data.cache.model.ProductEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(productEntities: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM products WHERE LOWER(title) LIKE LOWER(:searchQuery) ORDER BY price DESC")
    suspend fun searchProductsByTitle(searchQuery: String): List<ProductEntity>?

    @Query("SELECT * FROM products ORDER BY price DESC")
    suspend fun getProducts():List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
    suspend fun getProductById(productId: Int): ProductEntity?

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getProductCount(): Int

    @Query("UPDATE products SET bookMarked = :bookMarked WHERE id = :productId")
    suspend fun updateBookMarked(productId: Int, bookMarked: Boolean)
}