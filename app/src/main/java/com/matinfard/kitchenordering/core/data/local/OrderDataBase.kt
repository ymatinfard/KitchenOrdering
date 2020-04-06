package com.matinfard.kitchenordering.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matinfard.kitchenordering.data.model.OrderEntity
import com.matinfard.kitchenordering.data.model.OrderItemsEntity

/**
 * Saves order data in local storage permanently
 */
@Database(
    entities = [OrderItemsEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OrderDataBase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

}