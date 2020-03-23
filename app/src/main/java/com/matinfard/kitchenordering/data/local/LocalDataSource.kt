package com.matinfard.kitchenordering.data.local

import androidx.lifecycle.LiveData
import com.matinfard.kitchenordering.model.OrderEntity
import com.matinfard.kitchenordering.model.OrderItemsEntity

interface LocalDataSource{

    suspend fun getAllOrderItems(orderId: Int): List<OrderItemsEntity>

    fun getAllOrders(): LiveData<List<OrderEntity>>

    suspend fun insertOrderItems(orderItems: List<OrderItemsEntity>)

    suspend fun insertOrder(order: OrderEntity)

    suspend fun getTotalPrice(orderId: Int): Int

    suspend fun removeDatabaseInfo()
}