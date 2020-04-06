package com.matinfard.kitchenordering.data

import androidx.lifecycle.LiveData
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.functional.Either
import com.matinfard.kitchenordering.model.*

interface RepositoryOld {
    suspend fun getUserToken(userAuthData: UserAuthData): UserToken?
    suspend fun getProducts(userToken: String): Either<Failure, List<Product>>
    suspend fun saveOrderItems(orderItemsEntity: List<OrderItemsEntity>)
    suspend fun saveOrder(orderEntity: OrderEntity)
    suspend fun getAllOrderItems(orderId: Int): List<OrderItemsEntity>
    suspend fun getOrderItemsTotalPrice(orderId: Int): Int?
    suspend fun removeDatabaseInfo()
    fun getAllOrders(): LiveData<List<OrderEntity>>
}