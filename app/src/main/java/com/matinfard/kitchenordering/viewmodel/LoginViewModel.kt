package com.matinfard.kitchenordering.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.model.UserToken
import com.matinfard.kitchenordering.utils.Constants
import com.matinfard.kitchenordering.utils.SharedPrefToken
import com.matinfard.kitchenordering.utils.usernamePasswordSanitizer
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject



/**
 * Username must match email format, otherwise, log-in would fail. "usernamePasswordSanitizer" functions checks both username and password before
 * sending them to server.
 */
class LoginViewModel(
    private val repository: Repository) :
    ViewModel(), KoinComponent {

    private val sharedPrefToken : SharedPrefToken by inject()

    private var _isUserLoginSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    val isUserLoginSuccessful: LiveData<Boolean> = _isUserLoginSuccessful

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading


    fun validateUser(username: String, password: String) {
        viewModelScope.launch {
            usernamePasswordSanitizer(
                username,
                password
            ).also {
                isLoading(true)
            }?.let { userAuthdata ->
                val userTokenResult = async {
                    repository.getUserToken(userAuthdata)
                }.await()
                checkUserTokenAndUpdateUI(userTokenResult)
            }.also {
                isLoading(false)
            } ?: run {
                _isUserLoginSuccessful.value = Constants.LOGIN_FAILED
            }
        }
    }

    private fun checkUserTokenAndUpdateUI(tokenResult: UserToken?) {
        tokenResult?.let { userToken ->
            sharedPrefToken.userToken = tokenResult.token
            _isUserLoginSuccessful.value = Constants.LOGIN_SUCCESSFUL
        } ?: run {
            _isUserLoginSuccessful.value = Constants.LOGIN_FAILED
        }
    }

    private fun isLoading(status: Boolean) {
        _isLoading.value = status
    }

}