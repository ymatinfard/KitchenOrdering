package com.matinfard.kitchenordering.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data model of order items.
 */
@Entity(tableName = "order_items_table")
data class OrderItemsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int ,
    @ColumnInfo(name = "order_id")
    val orderId: Int,
    val guid: String,
    val name: String,
    val price: Int,
    val picture: String
)