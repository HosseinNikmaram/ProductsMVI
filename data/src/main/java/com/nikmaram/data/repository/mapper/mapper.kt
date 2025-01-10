package com.nikmaram.data.repository.mapper

import com.nikmaram.data.cache.model.ProductEntity
import com.nikmaram.data.network.dto.ProductDto
import com.nikmaram.data.network.dto.Rating

fun ProductEntity.toProductDto() : ProductDto{
    return ProductDto(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        rating = Rating(0.0,0)
    )
}

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
fun List<ProductEntity>.toProductDtoList(): List<ProductDto> {
    val returnableList = mutableListOf<ProductDto>()
    for (n in this.indices) {
        returnableList.add(this[n].toProductDto())
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