package com.nikmaram.data.repository.mapper

import com.nikmaram.data.cache.model.ProductEntity
import com.nikmaram.data.network.dto.ProductDto
import com.nikmaram.data.network.dto.Rating
import com.nikmaram.entity.Product

fun ProductDto.toProductEntity() : ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
    )
}

fun ProductEntity.toProduct() : Product{
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
    )
}

fun Product.toProductEntity() : ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
    )
}
fun List<ProductEntity>.toProductList(): List<Product> {
    val returnableList = mutableListOf<Product>()
    for (n in this.indices) {
        returnableList.add(this[n].toProduct())
    }
    return returnableList
}

fun List<ProductDto>.toProductEntityList() : List<ProductEntity> {
    val returnableList = mutableListOf<ProductEntity>()
    for (n in this.indices){
        returnableList.add(this[n].toProductEntity())
    }
    return returnableList
}