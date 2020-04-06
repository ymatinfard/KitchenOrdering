package com.matinfard.kitchenordering.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.data.model.UserAuthData
import com.matinfard.kitchenordering.data.source.FakeRepository
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.view.login.GetUserToken.Params
import com.matinfard.kitchenordering.view.login.LoginViewModel
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class LoginViewModelTest: AutoCloseKoinTest() {

    private val viewModel by inject<LoginViewModel>()
    private val inValidUserName = "username!"
    private val validUserName = "email@mail.com"
    private val validPassword = "password123"

    @get:Rule
    val instantExecuterRole = InstantTaskExecutorRule()

    @Before
    fun setup(){
        loadKoinModules(module {
            factory(override = true) { FakeRepository() as Repository }
        })
    }


    @Test
    fun test_user_login_failure() = runBlocking {

        viewModel.validateUser(inValidUserName, validPassword)

        viewModel.failure.observeForever {}
        val loginResult = viewModel.failure.value

        assertThat(loginResult, instanceOf(Failure.InvalidData.javaClass))
    }

    @Test
    fun test_user_login_success()  {

        viewModel.validateUser(validUserName, validPassword)

        viewModel.success.observeForever {}
        val isLoginSuccessful = viewModel.success.value

        assertThat(isLoginSuccessful, IsEqual(true))
    }
}
