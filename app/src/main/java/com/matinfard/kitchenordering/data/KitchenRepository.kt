package com.matinfard.kitchenordering.data

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.matinfard.kitchenordering.data.local.LocalDataSource
import com.matinfard.kitchenordering.model.*
import com.matinfard.kitchenordering.utils.testData

/**
 * Provides data for other components. Manges data retrieval from remote or local source.
 */
class KitchenRepository(
    private val kitchenRemoteDataSource: RemoteDataSource,
    private val kitchenLocalDataSource: LocalDataSource
) : Repository {
    override suspend fun getUserToken(userAuthData: UserAuthData): UserToken? {
        // kitchenRemoteDataSource.fetchUserToken(userAuthData)
        return UserToken("static-token")
    }


    override suspend fun getProducts(userToken: String): List<Product>? {
        // kitchenRemoteDataSource.fetchProducts(userToken)
        //providing static data
        val sType = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(testData, sType)
    }


    override suspend fun getOrderItemsTotalPrice(orderId: Int): Int? =
        kitchenLocalDataSource.getTotalPrice(orderId)

    override suspend fun removeDatabaseInfo() {
        kitchenLocalDataSource.removeDatabaseInfo()
    }

    override suspend fun saveOrderItems(orderEntity: List<OrderItemsEntity>) {
        kitchenLocalDataSource.insertOrderItems(orderEntity)
    }

    override suspend fun saveOrder(orderEntity: OrderEntity) {
        kitchenLocalDataSource.insertOrder(orderEntity)
    }

    override fun getAllOrders(): LiveData<List<OrderEntity>> = kitchenLocalDataSource.getAllOrders()

    override suspend fun getAllOrderItems(orderId: Int): List<OrderItemsEntity> =
        kitchenLocalDataSource.getAllOrderItems(orderId)

}


