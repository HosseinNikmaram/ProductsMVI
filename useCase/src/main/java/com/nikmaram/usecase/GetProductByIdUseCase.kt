package com.nikmaram.usecase

import com.nikmaram.data.repository.ProductRepository
import com.nikmaram.data.model.Product
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id:Int) : Result<Product?>{
        return productRepository.getProductById(id)
    }
}