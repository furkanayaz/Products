package com.fa.products.domain.mappers

import com.fa.products.data.dto.ProductDTO
import com.fa.products.domain.base.BaseMapper
import com.fa.products.domain.models.Product
import javax.inject.Inject

class ProductMapper @Inject constructor(): BaseMapper<ProductDTO, Product> {
    override fun ProductDTO.mapToModel(): Product {
        return Product(
            id = id,
            name = name
        )
    }

    override fun Product.mapToDto(): ProductDTO {
        return ProductDTO(
            id = id,
            name = name
        )
    }
}