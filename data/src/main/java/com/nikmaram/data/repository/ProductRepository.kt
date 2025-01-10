package com.nikmaram.data.repository

import com.nikmaram.entity.Product

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun getProductById(id:Int): Result<Product?>
    suspend fun getProductByTitle(title:String): Result<List<Product>?>
    suspend fun updateProduct(product:Product)
}