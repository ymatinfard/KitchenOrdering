package com.matinfard.kitchenordering.data.local

import com.matinfard.kitchenordering.data.model.OrderEntity
import com.matinfard.kitchenordering.data.model.OrderItemsEntity

interface LocalDataSource{

    suspend fun getAllOrderItems(orderId: Int): List<OrderItemsEntity>

    fun getAllOrders(): List<OrderEntity>

    suspend fun insertOrderItems(orderItems: List<OrderItemsEntity>)

    suspend fun insertOrder(order: OrderEntity)

    suspend fun getTotalPrice(orderId: Int): Int

    suspend fun removeDatabaseInfo()
}