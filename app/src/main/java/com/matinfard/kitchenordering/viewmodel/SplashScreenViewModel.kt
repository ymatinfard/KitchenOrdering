package com.matinfard.kitchenordering.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matinfard.kitchenordering.utils.Constants
import com.matinfard.kitchenordering.utils.SharedPrefToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Shows logo for 2 seconds when application is launched and checks user login status.
 * If user is already logged-in then it redirects to Dashboard fragment, otherwise login fragment.
 */
class SplashScreenViewModel(val context: Context) : ViewModel() {

    private val sharedPrefToken = SharedPrefToken(context)
    private var _isUserAlreadyLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    val isUserAlreadyLoggedIn = _isUserAlreadyLoggedIn

    init {
        viewModelScope.launch {
            delay(2000)
            checkUserLoginStatus()
        }
    }
    private fun checkUserLoginStatus(){
        sharedPrefToken.userToken?.let {
            _isUserAlreadyLoggedIn.postValue(Constants.USER_IS_LOGGED_IN)
        } ?: run {
            _isUserAlreadyLoggedIn.postValue(Constants.USER_IS_NOT_LOGGED_IN)
        }
    }
}