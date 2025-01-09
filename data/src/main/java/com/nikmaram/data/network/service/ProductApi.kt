package com.nikmaram.data.network.service

import com.nikmaram.data.network.Constants.PRODUCT_ROUT
import com.nikmaram.data.network.dto.ProductDto
import retrofit2.http.GET

interface ProductApi {
    @GET(PRODUCT_ROUT)
    suspend fun getAllProduct():List<ProductDto>
}