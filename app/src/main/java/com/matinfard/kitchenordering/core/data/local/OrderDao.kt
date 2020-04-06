package com.matinfard.kitchenordering.data.local

import androidx.room.*
import com.matinfard.kitchenordering.data.model.OrderEntity
import com.matinfard.kitchenordering.data.model.OrderItemsEntity

@Dao
interface OrderDao {

    @Query("SELECT * from order_items_table WHERE order_id = :orderId")
    suspend fun getAllOrderItems(orderId: Int): List<OrderItemsEntity>

    @Query("SELECT * from order_table ORDER BY date_time DESC")
    fun getAllOrders(): List<OrderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertOrderItems(orderItems: List<OrderItemsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertOrder(order: OrderEntity)

    @Query("SELECT SUM(price) FROM order_items_table WHERE order_id = :orderId")
    suspend fun getTotalPrice(orderId: Int): Int

    @Query("DELETE FROM order_items_table")
    suspend fun deleteAllOrderItems()

    @Query("DELETE FROM order_table")
    suspend fun deleteAllOrders()

    @Transaction
    suspend fun deleteAllTables(){
        deleteAllOrderItems()
        deleteAllOrders()
    }
}