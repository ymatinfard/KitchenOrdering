package com.matinfard.kitchenordering.data.local

import com.matinfard.kitchenordering.data.model.OrderEntity
import com.matinfard.kitchenordering.data.model.OrderItemsEntity

class KitchenLocalDataSource(private val orderDataBase: OrderDataBase) : LocalDataSource {

    override suspend fun getAllOrderItems(orderId: Int): List<OrderItemsEntity> =
        orderDataBase.orderDao().getAllOrderItems(orderId)

    override fun getAllOrders(): List<OrderEntity> {
        return orderDataBase.orderDao().getAllOrders()
    }

    override suspend fun insertOrderItems(orderItems: List<OrderItemsEntity>) =
        orderDataBase.orderDao().insertOrderItems(orderItems)

    override suspend fun insertOrder(order: OrderEntity) {
        orderDataBase.orderDao().insertOrder(order)
    }

    override suspend fun getTotalPrice(orderId: Int): Int =
        orderDataBase.orderDao().getTotalPrice(orderId)

    override suspend fun removeDatabaseInfo() {
        orderDataBase.orderDao().deleteAllTables()
    }

}