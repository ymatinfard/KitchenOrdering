package com.matinfard.kitchenordering.data

import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result
import com.matinfard.kitchenordering.data.model.*

interface Repository {

    suspend fun getUserToken(userAuthData: UserAuthData): Result<Failure, UserToken>
    suspend fun getProducts(userToken: String): Result<Failure, List<Product>>
    suspend fun saveOrderItems(orderItemsEntity: List<OrderItemsEntity>)
    suspend fun saveOrder(orderEntity: OrderEntity)
    suspend fun getAllOrderItems(orderId: Int): Result<Failure, List<OrderItemsEntity>>
    suspend fun getOrderItemsTotalPrice(orderId: Int): Result<Failure, Int>
    suspend fun removeDatabaseInfo()
    suspend fun getAllOrders(): Result<Failure, List<OrderEntity>>

}