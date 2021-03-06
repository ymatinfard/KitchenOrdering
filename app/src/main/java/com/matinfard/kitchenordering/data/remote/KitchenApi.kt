package com.matinfard.kitchenordering.data.remote

import com.matinfard.kitchenordering.model.Product
import com.matinfard.kitchenordering.model.UserAuthData
import com.matinfard.kitchenordering.model.UserToken
import com.matinfard.kitchenordering.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Remote api call
 */
interface KitchenApi {
    @POST(Constants.LOGIN_URL)
     suspend fun getUserToken(@Body body: UserAuthData): Response<UserToken>

    @GET(Constants.PRODUCT_URL)
    suspend fun getProducts(@Query("token") token: String): Response<List<Product>>
}