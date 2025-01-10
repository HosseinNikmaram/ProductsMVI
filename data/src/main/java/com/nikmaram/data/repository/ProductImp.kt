package com.nikmaram.data.repository

import com.nikmaram.data.cache.database.dao.ProductDao
import com.nikmaram.data.network.service.ProductApi
import com.nikmaram.data.repository.mapper.toProduct
import com.nikmaram.data.repository.mapper.toProductEntity
import com.nikmaram.data.repository.mapper.toProductEntityList
import com.nikmaram.data.repository.mapper.toProductList
import com.nikmaram.entity.Product
import javax.inject.Inject

class ProductImp  @Inject constructor(
    private val productApi: ProductApi,
    private val productDao: ProductDao,
    ):ProductRepository {
    override suspend fun getProducts(): Result<List<Product>> {
        if (productDao.getProductCount() == 0) {
            productDao.insertProducts(
                productApi.getAllProduct().toProductEntityList()
            )
        }
        return runCatching {
            productDao.getProducts().toProductList()
        }
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

    override suspend fun updateProduct(product: Product) {
            productDao.updateProduct(product.toProductEntity())
    }

}