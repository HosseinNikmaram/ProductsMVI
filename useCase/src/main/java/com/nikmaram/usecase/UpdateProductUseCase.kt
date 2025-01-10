package com.nikmaram.usecase

import com.nikmaram.data.network.dto.ProductDto
import com.nikmaram.data.repository.ProductRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product:ProductDto){
        return productRepository.updateProduct(
            product
        )
    }
}