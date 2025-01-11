package com.nikmaram.data.repository

import com.nikmaram.data.cache.database.dao.ProductDao
import com.nikmaram.data.network.service.ProductApi
import com.nikmaram.data.repository.mapper.toProduct
import com.nikmaram.data.repository.mapper.toProductEntity
import com.nikmaram.data.repository.mapper.toProductEntityList
import com.nikmaram.data.repository.mapper.toProductList
import com.nikmaram.data.model.Product
import javax.inject.Inject

class ProductImp  @Inject constructor(
    private val productApi: ProductApi,
    private val productDao: ProductDao,
    ):ProductRepository {
    override suspend fun getProducts(): Result<List<Product>> = runCatching {
        val productDtos = productApi.getAllProduct()
        val existingProducts = productDao.getProducts().associateBy { it.id }
        productDtos.map { productDto ->
            val existingProduct = existingProducts[productDto.id]
            val productEntity = productDto.toProductEntity().copy(
                bookMarked = existingProduct?.bookMarked ?: false
            )
            productDao.insertProduct(productEntity)
        }
        productDao.getProducts().toProductList()
    }.onFailure { error ->
        println("Error fetching or updating products: ${error.message}")
    }

    override suspend fun getProductById(id: Int): Result<Product?> {
        return runCatching {
            productDao.getProductById(id)?.toProduct()
        }
    }

    override suspend fun getProductByTitle(title: String): Result<List<Product>?> {
        return runCatching {
            productDao.searchProductsByTitle(title)?.toProductList()
        }
    }

    override suspend fun updateProduct(productId: Int, bookMarked: Boolean) {
            productDao.updateBookMarked(productId, bookMarked)
    }

}