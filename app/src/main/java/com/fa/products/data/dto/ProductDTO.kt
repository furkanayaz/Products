package com.fa.products.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val id: Int?,
    val name: String?
)