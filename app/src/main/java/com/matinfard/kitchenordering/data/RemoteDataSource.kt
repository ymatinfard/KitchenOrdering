package com.matinfard.kitchenordering.data

import com.matinfard.kitchenordering.model.Product
import com.matinfard.kitchenordering.model.UserAuthData
import com.matinfard.kitchenordering.model.UserToken

interface RemoteDataSource {
     suspend fun fetchUserToken(userAuthData: UserAuthData): UserToken?
     suspend fun fetchProducts(userToken: String): List<Product>?
}