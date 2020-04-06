package com.matinfard.kitchenordering.data.source

import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.data.model.*
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result


class FakeRepository: Repository {

    private val productTest1 = Product("13085ace-fd17-4560-9614-426fd45823cd", "Test Product 1", 1000, "http://placehold.it/32x32", "This is a test description for product" )
    private val productTest2 = Product("f0b19dad-934e-44d8-97cd-0c8d8bd1360a", "Test Product 2", 2000, "http://placehold.it/32x32", "This is a test description for product" )
    private val productTest3 = Product("6a796fe4-0ed1-40fe-96ea-a0ace81a5282", "Test Product 3", 3000, "http://placehold.it/32x32", "This is a test description for product" )
    private val productListTest = listOf(productTest1, productTest2, productTest3)
    private val orderItem1 = OrderItemsEntity(1, 12345, "2222-3333-4444-5555-6666", "item test 1", 1000, "www.placeholder.io")
    private val orderItem2 = OrderItemsEntity(2, 12345, "7777-8888-9999-1111-0000", "item test 2", 3000, "www.placeholder.io")
    private val orderTest1 : List<OrderEntity> = listOf(OrderEntity(12345, "2020-3-1 07:15", 2, 4000))
    private val orderItemsTest = listOf(orderItem1, orderItem2)

    override suspend fun getUserToken(userAuthData: UserAuthData): Result<Failure, UserToken> {
       return Result.Success(UserToken("token"))
    }

    override suspend fun getProducts(userToken: String): Result<Failure, List<Product>> {
        return Result.Success(productListTest )
    }

    override suspend fun saveOrderItems(orderItemsEntity: List<OrderItemsEntity>) {
    }

    override suspend fun saveOrder(orderEntity: OrderEntity) {
    }

    override suspend fun getAllOrderItems(orderId: Int): Result<Failure, List<OrderItemsEntity>> {
        return Result.Success(orderItemsTest)
    }

    override suspend fun getOrderItemsTotalPrice(orderId: Int): Result<Failure, Int> {
        return Result.Success(orderItemsTest.map { it.price }.sum())
    }

    override suspend fun removeDatabaseInfo() {
    }

    override suspend fun getAllOrders(): Result<Failure, List<OrderEntity>> {
        return Result.Success(orderTest1)
    }
}