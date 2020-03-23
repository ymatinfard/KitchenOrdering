package com.matinfard.kitchenordering.data

import androidx.lifecycle.LiveData
import com.matinfard.kitchenordering.model.*

interface Repository {
    suspend fun getUserToken(userAuthData: UserAuthData): UserToken?
    suspend fun getProducts(userToken: String): List<Product>?
    suspend fun saveOrderItems(orderItemsEntity: List<OrderItemsEntity>)
    suspend fun saveOrder(orderEntity: OrderEntity)
    suspend fun getAllOrderItems(orderId: Int): List<OrderItemsEntity>
    suspend fun getOrderItemsTotalPrice(orderId: Int): Int?
    suspend fun removeDatabaseInfo()
    fun getAllOrders(): LiveData<List<OrderEntity>>
}