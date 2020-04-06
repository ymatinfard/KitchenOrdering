package com.matinfard.kitchenordering.data.model

/**
 * Data model of product
 */
data class Product(
    val guid: String,
    val name: String,
    val price: Int,
    val picture: String,
    val description: String,
    var selected: Boolean = false
)