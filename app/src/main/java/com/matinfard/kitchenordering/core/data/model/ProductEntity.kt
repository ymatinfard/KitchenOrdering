package com.matinfard.kitchenordering.data.model

/**
 * Data model of product
 */
data class ProductEntity(
    private val guid: String,
    private val name: String,
    private val price: Int,
    private val picture: String,
    private val description: String
){
    fun toProduct() = Product(guid, name, price, picture, description)
}