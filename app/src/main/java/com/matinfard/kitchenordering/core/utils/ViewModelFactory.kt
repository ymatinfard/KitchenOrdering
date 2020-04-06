
package com.matinfard.kitchenordering.utils

/**
 * Factory for all ViewModels.
 */
//@Suppress("UNCHECKED_CAST")
//class ViewModelFactory constructor(
//    private val kitchenRepository: Repository,
//    owner: SavedStateRegistryOwner,
//    defaultArgs: Bundle? = null
//) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
//
//    override fun <T : ViewModel> create(
//            key: String,
//            modelClass: Class<T>,
//            handle: SavedStateHandle
//    ) = with(modelClass) {
//        when {
//            isAssignableFrom(LoginViewModel::class.java) ->
//                LoginViewModel( kitchenRepository)
//            isAssignableFrom(SettingsViewModel::class.java) ->
//                SettingsViewModel( kitchenRepository)
//            isAssignableFrom(SplashScreenViewModel::class.java) ->
//                SplashScreenViewModel()
//        //    isAssignableFrom(ViewListSharedViewModel::class.java) ->
//          //      ViewListSharedViewModel( kitchenRepository)
//            else ->
//                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
//        }
//    } as T
//}
