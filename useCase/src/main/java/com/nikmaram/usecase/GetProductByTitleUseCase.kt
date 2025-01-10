package com.nikmaram.usecase

import com.nikmaram.data.network.dto.ProductDto
import com.nikmaram.data.repository.ProductRepository
import javax.inject.Inject

class GetProductByTitleUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(title:String) : Result<List<ProductDto>?>{
        return productRepository.getProductByTitle(title)
    }
}