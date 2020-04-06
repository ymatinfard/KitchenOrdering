//package com.matinfard.kitchenordering.data.source
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.matinfard.kitchenordering.data.Repository
//import com.matinfard.kitchenordering.exception.Failure
//import com.matinfard.kitchenordering.core.functional.Result
//import com.matinfard.kitchenordering.data.model.*
//import kotlinx.coroutines.runBlocking
//
//
//class FakeKitchenRepository : Repository {
//
//    private val productEntityTest = Product(
//        "13085ace-fd17-4560-9614-426fd45823cd",
//        "Test Product 1",
//        1000,
//        "http://placehold.it/32x32",
//        "This is a test description for product"
//    )
//
//    private val orderEntityTest = OrderEntity(
//        12,
//        "2020-3-1 07:15",
//        2,
//        4000
//    )
//
//    private val orderItemEntityTest = OrderItemsEntity(
//        0,
//        12345,
//        "abcd-dffad-addfe-okid",
//        "order item",
//        1000,
//        "http://placehold.it/32x32"
//    )
//
//    private val orderTest1: List<OrderEntity> = listOf(orderEntityTest)
//    private var orderList1LiveDataTest: MutableLiveData<List<OrderEntity>> = MutableLiveData()
//
//    private val productListTest = listOf(productEntityTest)
//
//    private var orderListTest = mutableListOf<OrderEntity>()
//    private var orderItemsListTest = mutableListOf<OrderItemsEntity>()
//
//    override suspend fun getUserToken(userAuthData: UserAuthData): UserToken? {
//        return UserToken("5e8c3c48-af49-425b-a6d9-f37f3511e4fa")
//    }
//
//    override suspend fun getProducts(userToken: String): Result<Failure, List<Product>> {
//        TODO("Not yet implemented")
//    }
//
////    override suspend fun getProducts(userToken: String): List<Product>? {
////        return productListTest
////    }
//
//    override suspend fun saveOrderItems(orderItemsEntity: List<OrderItemsEntity>) {
//        runBlocking { orderItemsListTest.addAll(orderItemsEntity) }
//    }
//
//    override suspend fun saveOrder(orderEntity: OrderEntity) {
//        runBlocking { orderListTest.add(orderEntity) }
//
//    }
//
//    override suspend fun getAllOrders(): LiveData<List<OrderEntity>> {
//        runBlocking { orderList1LiveDataTest.postValue(orderTest1) }
//        return orderList1LiveDataTest
//    }
//
//    override suspend fun getAllOrderItems(orderId: Int): List<OrderItemsEntity> {
//        return listOf(orderItemEntityTest)
//    }
//
//    override suspend fun getOrderItemsTotalPrice(orderId: Int): Int? {
//        return orderItemsListTest.map { it.price }.sum()
//    }
//
//    override suspend fun removeDatabaseInfo() {
//    }
//
//}