package com.nikmaram.usecase

import com.nikmaram.data.network.dto.ProductDto
import com.nikmaram.data.repository.ProductRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id:Int) : Result<ProductDto?>{
        return productRepository.getProductById(id)
    }
}