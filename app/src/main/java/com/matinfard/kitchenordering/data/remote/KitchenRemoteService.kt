package com.matinfard.kitchenordering.data.remote

import com.matinfard.kitchenordering.data.RemoteDataSource
import com.matinfard.kitchenordering.model.Product
import com.matinfard.kitchenordering.model.UserAuthData
import com.matinfard.kitchenordering.model.UserToken
import com.matinfard.kitchenordering.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.lang.Exception

/**
 * Handles all remote api calls. It returns "null" if remote call is not successful.
 */
class KitchenRemoteService: RemoteDataSource, KoinComponent {

    private val retrofit: Retrofit by inject()

    private val kitchenService = retrofit.create(KitchenApi::class.java)

    override suspend fun fetchUserToken(userAuthData: UserAuthData): UserToken? = withContext(Dispatchers.IO) {
        try {
            val result = kitchenService.getUserToken(userAuthData)
            if (result.isSuccessful) result.body() else null
        } catch (ex: Exception) {
            Timber.e(ex.message)
            null
        }
    }

    override suspend fun fetchProducts(userToken: String): List<Product>? = withContext(Dispatchers.IO) {
        try {
            val result = kitchenService.getProducts(userToken)
            if (result.isSuccessful) result.body() else null
        } catch (ex: Exception) {
            Timber.e(ex.message)
            null
        }
    }

}