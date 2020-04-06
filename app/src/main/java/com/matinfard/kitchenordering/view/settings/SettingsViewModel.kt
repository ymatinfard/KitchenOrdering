package com.matinfard.kitchenordering.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matinfard.kitchenordering.view.settings.RemoveDatabaseInfo
import com.matinfard.kitchenordering.core.interactor.UseCase
import com.matinfard.kitchenordering.utils.Constants
import com.matinfard.kitchenordering.utils.SharedPrefToken
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class SettingsViewModel(
    private val removeDatabaseInfo: RemoveDatabaseInfo,
    private val sharedPrefToken: SharedPrefToken
) : ViewModel(), KoinComponent {

    private val _logoutLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val logout: LiveData<Boolean> = _logoutLiveData

    fun logout() = viewModelScope.launch {
        sharedPrefToken.userToken = Constants.USER_TOKEN_DEFAULT_VAL
        removeDatabaseInfo(UseCase.None())
        _logoutLiveData.value = Constants.LOGOUT
    }
}