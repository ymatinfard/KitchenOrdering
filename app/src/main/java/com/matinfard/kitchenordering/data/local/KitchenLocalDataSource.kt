package com.matinfard.kitchenordering.data.local

import androidx.lifecycle.LiveData
import com.matinfard.kitchenordering.model.OrderEntity
import com.matinfard.kitchenordering.model.OrderItemsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class KitchenLocalDataSource(private val orderDataBase: OrderDataBase) : LocalDataSource {
    override suspend fun getAllOrderItems(orderId: Int): List<OrderItemsEntity> =
        withContext(Dispatchers.IO) {
            return@withContext orderDataBase.orderDao().getAllOrderItems(orderId)
        }

    override fun getAllOrders(): LiveData<List<OrderEntity>> {
        return orderDataBase.orderDao().getAllOrders()
    }

    override suspend fun insertOrderItems(orderItems: List<OrderItemsEntity>) =
        withContext(Dispatchers.IO) {
            orderDataBase.orderDao().insertOrderItems(orderItems)
        }

    override suspend fun insertOrder(order: OrderEntity) = withContext(Dispatchers.IO) {
        orderDataBase.orderDao().insertOrder(order)
    }

    override suspend fun getTotalPrice(orderId: Int): Int = withContext(Dispatchers.IO) {
        return@withContext orderDataBase.orderDao().getTotalPrice(orderId)
    }

    override suspend fun removeDatabaseInfo() = withContext(Dispatchers.IO) {
        coroutineScope {
            orderDataBase.orderDao().deleteAllOrderItems()
            orderDataBase.orderDao().deleteAllOrders()
        }
    }

}