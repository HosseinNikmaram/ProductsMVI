package com.nikmaram.data.repository

import com.nikmaram.data.network.dto.ProductDto

interface ProductRepository {
    suspend fun getProducts(): Result<List<ProductDto>>
    suspend fun getProductById(id:Int): Result<ProductDto?>
    suspend fun getProductByTitle(title:String): Result<List<ProductDto>?>
    suspend fun updateProduct(product:ProductDto)
}