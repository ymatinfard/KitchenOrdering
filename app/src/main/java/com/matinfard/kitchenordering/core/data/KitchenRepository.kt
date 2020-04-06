package com.matinfard.kitchenordering.data

import com.matinfard.kitchenordering.data.local.LocalDataSource
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result
import com.matinfard.kitchenordering.data.model.*
import com.matinfard.kitchenordering.platform.NetworkHandler
import org.koin.core.KoinComponent

/**
 * Provides data for other components. Manges data retrieval from remote or local source.
 */

class KitchenRepository(
    private val kitchenRemoteDataSource: RemoteDataSource,
    private val kitchenLocalDataSource: LocalDataSource,
    private val networkHandler: NetworkHandler
) : Repository, BaseDataSource(), KoinComponent {

    override suspend fun getUserToken(userAuthData: UserAuthData): Result<Failure, UserToken> =
         request({ kitchenRemoteDataSource.fetchUserToken(userAuthData) },{
             it.toUserToken() }, UserTokenEntity(null))


    override suspend fun getProducts(userToken: String): Result<Failure, List<Product>> {
        // kitchenRemoteDataSource.fetchProducts(userToken)
        //providing static data
//        val sType = object : TypeToken<List<Product>>() {}.type
//        return Gson().fromJson(testData, sType)
        return when (networkHandler.isConnected()) {
            true -> request({ kitchenRemoteDataSource.fetchProducts(userToken) }, {
                it.map { it.toProduct() }
            }, emptyList())
            false, null -> Result.Failure(Failure.NetworkConnection)
        }
    }

    override suspend fun getAllOrders(): Result<Failure, List<OrderEntity>> =
         request({ kitchenLocalDataSource.getAllOrders() }, emptyList())


    override suspend fun getOrderItemsTotalPrice(orderId: Int): Result<Failure, Int> =
        request({ kitchenLocalDataSource.getTotalPrice(orderId) }, 0)

    override suspend fun removeDatabaseInfo() {
        request({ kitchenLocalDataSource.removeDatabaseInfo() }, Unit)
    }

    override suspend fun saveOrderItems(orderEntity: List<OrderItemsEntity>) {
        request({ kitchenLocalDataSource.insertOrderItems(orderEntity) }, Unit)
    }

    override suspend fun saveOrder(orderEntity: OrderEntity) {
        request({ kitchenLocalDataSource.insertOrder(orderEntity) }, Unit)
    }

    override suspend fun getAllOrderItems(orderId: Int): Result<Failure, List<OrderItemsEntity>> =
        request({ kitchenLocalDataSource.getAllOrderItems(orderId) }, emptyList())
}



