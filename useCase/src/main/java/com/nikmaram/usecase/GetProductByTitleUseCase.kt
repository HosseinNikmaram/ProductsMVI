package com.nikmaram.usecase

import com.nikmaram.data.repository.ProductRepository
import com.nikmaram.data.model.Product
import javax.inject.Inject

class GetProductByTitleUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(title:String) : Result<List<Product>?>{
        return productRepository.getProductByTitle(title)
    }
}