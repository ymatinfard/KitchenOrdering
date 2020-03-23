package com.matinfard.kitchenordering.data.source

import androidx.lifecycle.LiveData
import com.matinfard.kitchenordering.data.local.LocalDataSource
import com.matinfard.kitchenordering.model.OrderEntity
import com.matinfard.kitchenordering.model.OrderItemsEntity

class FakeLocalDataSource: LocalDataSource {
    private val orderItem1 = OrderItemsEntity(1, 12345, "2222-3333-4444-5555-6666", "item test 1", 1000, "www.placeholder.io")
    private val orderItem2 = OrderItemsEntity(2, 12345, "7777-8888-9999-1111-0000", "item test 2", 3000, "www.placeholder.io")
    private val orderTest1 : List<OrderEntity> = listOf(OrderEntity(12345, "2020-3-1 07:15", 2, 4000))
    private val orderItemsTest = listOf(orderItem1, orderItem2)
    override suspend fun getAllOrderItems(orderId: Int): List<OrderItemsEntity> {
        return orderItemsTest
    }

    override fun getAllOrders(): LiveData<List<OrderEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
     }

    override suspend fun insertOrderItems(orderItems: List<OrderItemsEntity>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertOrder(order: OrderEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getTotalPrice(orderId: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun removeDatabaseInfo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}