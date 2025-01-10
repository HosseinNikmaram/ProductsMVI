package com.nikmaram.data.repository

import com.nikmaram.data.cache.database.dao.ProductDao
import com.nikmaram.data.network.dto.ProductDto
import com.nikmaram.data.network.service.ProductApi
import com.nikmaram.data.repository.mapper.toProductDto
import com.nikmaram.data.repository.mapper.toProductDtoList
import com.nikmaram.data.repository.mapper.toProductEntity
import com.nikmaram.data.repository.mapper.toProductEntityList
import javax.inject.Inject

class ProductImp  @Inject constructor(
    private val productApi: ProductApi,
    private val productDao: ProductDao,
    ):ProductRepository {
    override suspend fun getProducts(): Result<List<ProductDto>> {
        if (productDao.getProductCount() == 0) {
            productDao.insertProducts(
                productApi.getAllProduct().toProductEntityList()
            )
        }
        return runCatching {
            productDao.getProducts().toProductDtoList()
        }
    }

    override suspend fun getProductById(id: Int): Result<ProductDto?> {
        return runCatching {
            productDao.getProductById(id)?.toProductDto()
        }
    }

    override suspend fun getProductByTitle(title: String): Result<List<ProductDto>?> {
        return runCatching {
            productDao.searchProductsByTitle(title)?.toProductDtoList()
        }
    }

    override suspend fun updateProduct(product: ProductDto) {
            productDao.updateProduct(product.toProductEntity())
    }

}