package com.matinfard.kitchenordering.data

import com.matinfard.kitchenordering.data.model.*
import retrofit2.Response

interface RemoteDataSource {
     suspend fun fetchUserToken(userAuthData: UserAuthData): Response<UserTokenEntity>
     suspend fun fetchProducts(userToken: String): Response<List<ProductEntity>>
}