package com.matinfard.kitchenordering.utils

import android.util.Patterns
import android.view.View
import com.matinfard.kitchenordering.data.model.UserAuthData
import kotlinx.android.synthetic.main.fragment_view_list.view.*

/**
 * Utility functions for components
 */
fun usernamePasswordSanitizer(username: String, password: String): UserAuthData? {
    return if ((username.trim().isNotEmpty()
                && Patterns.EMAIL_ADDRESS.toRegex().matches(username)) && (password.trim().isNotEmpty())
    )
        UserAuthData(username, password)
    else
        null
}

fun dataIsLoading(view: View, isLoading: Boolean) {
    view.progressbar_product.visibility = if (isLoading) View.VISIBLE else View.GONE
}



