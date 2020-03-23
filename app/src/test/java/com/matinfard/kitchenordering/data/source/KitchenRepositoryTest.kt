package com.matinfard.kitchenordering.data.source

import com.matinfard.kitchenordering.data.KitchenRepository
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.model.UserAuthData
import com.matinfard.kitchenordering.model.UserToken
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

class KitchenRepositoryTest {

    private lateinit var kitchenRepository: Repository
    private val tokenTest = UserToken("5e8c3c48-af49-425b-a6d9-f37f3511e4fa")

    @Before
    fun createRepository() {
        val fakeRemoteDataSource = FakeRemoteDataSource(tokenTest)
        val fakeLocalDataSource = FakeLocalDataSource()
        kitchenRepository = KitchenRepository(fakeRemoteDataSource, fakeLocalDataSource)
    }

    @Test
    fun getUserTokenFromRemoteDataSource() = runBlocking {
        val userToken = kitchenRepository.getUserToken(UserAuthData("email@mail.com", "password"))
        assertThat(userToken, IsEqual(tokenTest))
    }

    @Test
    fun getAllOrderItemsFromLocalDataSource() = runBlocking {
        val items = kitchenRepository.getAllOrderItems(1234)
        assertThat(items.size, IsEqual(2))
    }


}