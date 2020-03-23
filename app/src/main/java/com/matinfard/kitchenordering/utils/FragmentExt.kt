package com.matinfard.kitchenordering.utils

/**
 * Extension functions for Fragment.
 */

import android.content.Context
import androidx.fragment.app.Fragment
import com.matinfard.kitchenordering.KitchenApplication


fun Fragment.getViewModelFactory(context: Context): ViewModelFactory {
    val repository = (requireContext().applicationContext as KitchenApplication).kitchenRepository
    return ViewModelFactory( context, repository, this)
}
