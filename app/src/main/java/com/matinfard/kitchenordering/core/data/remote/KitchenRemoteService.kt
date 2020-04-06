package com.matinfard.kitchenordering.data.remote

import android.util.Log
import com.matinfard.kitchenordering.data.RemoteDataSource
import com.matinfard.kitchenordering.data.model.ProductEntity
import com.matinfard.kitchenordering.data.model.UserAuthData
import com.matinfard.kitchenordering.data.model.UserTokenEntity
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Handles all remote api calls. It returns "null" if remote call is not successful.
 */
class KitchenRemoteService : RemoteDataSource, KoinComponent {

    private val retrofit: Retrofit by inject()

    private val kitchenService = retrofit.create(KitchenApi::class.java)

    override suspend fun fetchUserToken(userAuthData: UserAuthData): Response<UserTokenEntity> =
        kitchenService.getUserToken(userAuthData)

    override suspend fun fetchProducts(userToken: String): Response<List<ProductEntity>> {
        Log.d("Yousef", "fetch product called")
        return kitchenService.getProducts(userToken)
    }

}

//    override suspend fun fetchProducts(userToken: String): List<Product>? = withContext(Dispatchers.IO) {
//        try {
//            val result = kitchenService.getProducts(userToken)
//            if (result.isSuccessful) result.body() else null
//        } catch (ex: Exception) {
//            Timber.e(ex.message)
//            null
//        }
//    }
//fun fetchProducts(userToken: String): Either<Failure, List<Product>> {
//    return
//}

