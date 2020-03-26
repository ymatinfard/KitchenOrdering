package com.matinfard.kitchenordering.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.utils.Constants
import com.matinfard.kitchenordering.utils.SharedPrefToken
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class SettingsViewModel(private val repository: Repository) : ViewModel(), KoinComponent {

    private val sharedPrefToken: SharedPrefToken by inject()

    private val _logoutLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val logout: LiveData<Boolean> = _logoutLiveData

    fun logout() = viewModelScope.launch {
//        val sharedProf = SharedPrefToken(context)
        sharedPrefToken.userToken = Constants.USER_TOKEN_DEFAULT_VAL
        repository.removeDatabaseInfo()
        _logoutLiveData.value = Constants.LOGOUT
    }
}