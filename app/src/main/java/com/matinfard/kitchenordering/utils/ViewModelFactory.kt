
package com.matinfard.kitchenordering.utils

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.viewmodel.LoginViewModel
import com.matinfard.kitchenordering.viewmodel.SettingsViewModel
import com.matinfard.kitchenordering.viewmodel.SplashScreenViewModel
import com.matinfard.kitchenordering.viewmodel.ViewListSharedViewModel

/**
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val context: Context,
                                   private val kitchenRepository: Repository,
                                   owner: SavedStateRegistryOwner,
                                   defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(context, kitchenRepository)
            isAssignableFrom(SettingsViewModel::class.java) ->
                SettingsViewModel(context, kitchenRepository)
            isAssignableFrom(SplashScreenViewModel::class.java) ->
                SplashScreenViewModel(context)
            isAssignableFrom(ViewListSharedViewModel::class.java) ->
                ViewListSharedViewModel(context, kitchenRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
