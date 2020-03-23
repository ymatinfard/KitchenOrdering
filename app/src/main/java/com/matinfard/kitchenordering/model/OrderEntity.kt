package com.matinfard.kitchenordering.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data model of Order
 */
@Entity(tableName = "order_table")
data class OrderEntity (
    @PrimaryKey
    @ColumnInfo(name = "order_id")
    val orderId: Int,
    @ColumnInfo(name = "date_time")
    var dateTime: String,
    val count: Int,
    @ColumnInfo(name = "total_price")
    val totalPrice: Int
)