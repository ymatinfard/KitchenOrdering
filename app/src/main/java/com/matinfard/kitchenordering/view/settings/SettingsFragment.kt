package com.matinfard.kitchenordering.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.viewmodel.SettingsViewModel
import kotlinx.android.synthetic.main.fragment_settings.view.*
import org.koin.android.ext.android.inject

/**
 * Provides logout feature. When logging out, all user data (token in sharedPref, orders saved in room database) will be removed.
 */
class SettingsFragment: BaseFragment() {

    private val mViewModel: SettingsViewModel by inject()

    override fun layoutId(): Int = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()
        setObservers()
    }

    private fun setObservers() {
        mViewModel.logout.observe(viewLifecycleOwner){ isLoggedOut -> if (isLoggedOut) logout() }
    }

    private fun setOnClick() { mView.btn_logout.setOnClickListener { mViewModel.logout() } }

    private fun logout(){ findNavController().navigate(R.id.action_settingsFragment_to_loginFragment) }
}