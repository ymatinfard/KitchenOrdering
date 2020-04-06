package com.matinfard.kitchenordering.view

import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.data.model.*
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result


class FakeRepository: Repository {
    override suspend fun getUserToken(userAuthData: UserAuthData): Result<Failure, UserToken> {
       return Result.Success(UserToken("token"))
    }

    override suspend fun getProducts(userToken: String): Result<Failure, List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveOrderItems(orderItemsEntity: List<OrderItemsEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun saveOrder(orderEntity: OrderEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllOrderItems(orderId: Int): Result<Failure, List<OrderItemsEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOrderItemsTotalPrice(orderId: Int): Result<Failure, Int> {
        TODO("Not yet implemented")
    }

    override suspend fun removeDatabaseInfo() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllOrders(): Result<Failure, List<OrderEntity>> {
        TODO("Not yet implemented")
    }
}