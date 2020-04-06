package com.matinfard.kitchenordering.data.source

import com.matinfard.kitchenordering.data.RemoteDataSource
import com.matinfard.kitchenordering.data.model.*
import retrofit2.Response

class FakeRemoteDataSource() : RemoteDataSource {

    private val productTest1 = ProductEntity("13085ace-fd17-4560-9614-426fd45823cd", "Test Product 1", 1000, "http://placehold.it/32x32", "This is a test description for product" )
    private val productTest2 = ProductEntity("f0b19dad-934e-44d8-97cd-0c8d8bd1360a", "Test Product 2", 2000, "http://placehold.it/32x32", "This is a test description for product" )
    private val productTest3 = ProductEntity("6a796fe4-0ed1-40fe-96ea-a0ace81a5282", "Test Product 3", 3000, "http://placehold.it/32x32", "This is a test description for product" )
    private val productListTest = listOf(productTest1, productTest2, productTest3)

    override suspend fun fetchUserToken(userAuthData: UserAuthData): Response<UserTokenEntity> {
            return Response.success(UserTokenEntity("token"))
    }

    override suspend fun fetchProducts(userToken: String): Response<List<ProductEntity>> {
        return Response.success(productListTest)
    }
}