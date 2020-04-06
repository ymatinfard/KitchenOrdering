package com.matinfard.kitchenordering.view.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.view.login.GetUserToken
import com.matinfard.kitchenordering.model.UserToken
import com.matinfard.kitchenordering.utils.SharedPrefToken
import com.matinfard.kitchenordering.utils.SingleLiveEvent
import com.matinfard.kitchenordering.utils.usernamePasswordSanitizer
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent


/**
 * Username must match email format, otherwise, log-in would fail. "usernamePasswordSanitizer" functions checks both username and password before
 * sending them to server.
 */

class LoginViewModel(
    private val getUserToken: GetUserToken,
    private val sharedPrefToken: SharedPrefToken
) : ViewModel(), KoinComponent {

    val _success = SingleLiveEvent<Any>()
    val success: LiveData<Any>
        get() = _success

    private var _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: MutableLiveData<Boolean> = _loading

    private var _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure


    fun validateUser(username: String, password: String) {
        viewModelScope.launch {
            usernamePasswordSanitizer(
                username,
                password
            )?.let { userAuthData ->
                getUserToken(GetUserToken.Params(userAuthData)) {
                    it.fold(::handleFailure, ::handleUserToken)
                }
            } ?: run {
                handleFailure(Failure.InvalidData)
            }
        }
    }

    private fun handleUserToken(userToken: UserToken) {
        userToken.token?.let { token ->
            sharedPrefToken.userToken = token
            _success.call()
        } ?: run {
            handleFailure(Failure.InvalidData)
        }
    }

    private fun handleFailure(failure: Failure) {
        Log.e("Yousef", "Login error")
        _failure.value = failure
    }

    private fun isLoading(status: Boolean) {
        _loading.value = status
    }

}