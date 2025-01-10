package com.nikmaram.usecase

import com.nikmaram.data.repository.ProductRepository
import javax.inject.Inject

class UpdateBookMarkUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: Int, bookMarked: Boolean){
        return productRepository.updateProduct(
            productId, bookMarked
        )
    }
}