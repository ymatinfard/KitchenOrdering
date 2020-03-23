package com.matinfard.kitchenordering.utils

import android.content.Context

/**
 * Keeps user token permanently.
 */

class SharedPrefToken(context: Context) {

    private val prefs =
        context.getSharedPreferences(Constants.SHARED_PREF_NAME, Constants.SHARE_PREF_PRIVATE_MODE)

    var userToken: String?
        get() = prefs.getString(Constants.USER_TOKEN, Constants.USER_TOKEN_DEFAULT_VAL)
        set(value) = prefs.edit().putString(Constants.USER_TOKEN, value).apply()
}