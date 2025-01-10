package com.nikmaram.usecase

import com.nikmaram.data.network.dto.ProductDto
import com.nikmaram.data.repository.ProductRepository
import com.nikmaram.entity.Product
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product){
        return productRepository.updateProduct(
            product
        )
    }
}