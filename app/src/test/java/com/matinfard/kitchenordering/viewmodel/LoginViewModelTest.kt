package com.matinfard.kitchenordering.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.data.source.FakeKitchenRepository
import com.matinfard.kitchenordering.utils.Constants
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var fakeRepository: Repository
    private lateinit var context: Context
    private val inValidUserName = "username!"
    private val validUserName = "email@mail.com"
    private val validPassword = "password123"

    @get:Rule
    val instantExecuterRole = InstantTaskExecutorRule()

    @Before
    fun setup_viewmodel() {
        context = ApplicationProvider.getApplicationContext()
        fakeRepository = FakeKitchenRepository()
        loginViewModel = LoginViewModel(context, fakeRepository)
    }

    @Test
    fun test_user_login_failure() = runBlocking {
        loginViewModel.validateUser(inValidUserName, validPassword)

        loginViewModel.isUserLoginSuccessful.observeForever {}
        val loginResult = loginViewModel.isUserLoginSuccessful.value
        assertThat(loginResult, IsEqual(Constants.LOGIN_FAILED))
    }

    @Test
    fun test_user_login_success() = runBlocking {
        loginViewModel.validateUser(validUserName, validPassword)

        loginViewModel.isUserLoginSuccessful.observeForever {}
        val loginResult = loginViewModel.isUserLoginSuccessful.value
        assertThat(loginResult, IsEqual(Constants.LOGIN_SUCCESSFUL))
    }
}
