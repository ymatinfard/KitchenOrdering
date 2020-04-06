package com.matinfard.kitchenordering.data.source

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.matinfard.kitchenordering.data.RemoteDataSource
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.data.local.LocalDataSource
import com.matinfard.kitchenordering.data.model.UserAuthData
import com.matinfard.kitchenordering.data.model.UserToken
import com.matinfard.kitchenordering.core.functional.Result
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.samePropertyValuesAs
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class KitchenRepositoryTest: AutoCloseKoinTest() {

    private val kitchenRepository by inject<Repository>()
    @Before
    fun setup() {

        loadKoinModules(
            module{
                factory(override = true) {FakeLocalDataSource() as LocalDataSource}
            }
        )
        loadKoinModules(
            module {
                factory (override = true) {FakeRemoteDataSource() as RemoteDataSource}
            }
        )


    }

    @Test
    fun getUserTokenFromRemoteDataSource() = runBlocking {
        val result = kitchenRepository.getUserToken(UserAuthData("email@mail.com", "password"))

        assertThat(result.isSuccessful, IsEqual(true))
        assertThat((result as Result.Success).b, samePropertyValuesAs(UserToken("token")))
    }

    @Test
    fun getAllOrderItemsFromLocalDataSource() = runBlocking {
        val result = kitchenRepository.getAllOrderItems(1234)

        assertThat(result.isSuccessful, IsEqual(true))
    }


}