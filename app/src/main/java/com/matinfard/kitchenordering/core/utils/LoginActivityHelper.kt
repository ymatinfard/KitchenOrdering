package com.matinfard.kitchenordering.utils

import android.view.View
import com.matinfard.kitchenordering.R
import kotlinx.android.synthetic.main.fragment_login.view.*

fun progressbarVisibilityStatus(
    view: View,
    isLoading: Boolean
) {
    if (isLoading) {
        view.btn_login.setBackgroundResource(R.drawable.border_login_button_deactive)
        view.progressbar.visibility = View.VISIBLE
        view.tv_login_error.text = ""
    } else {
        view.btn_login.setBackgroundResource(R.drawable.border_login_button)
        view.progressbar.visibility = View.GONE
    }
    view.apply {
        et_username.isEnabled = !isLoading
        et_password.isEnabled = !isLoading
        btn_login.isEnabled = !isLoading
    }
}

fun showErrorMessage(view: View) {
    view.tv_login_error.text = Constants.LOGIN_ERROR_MESSAGE
}

