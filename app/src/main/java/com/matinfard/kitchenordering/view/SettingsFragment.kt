package com.matinfard.kitchenordering.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.utils.getViewModelFactory
import com.matinfard.kitchenordering.viewmodel.SettingsViewModel
import kotlinx.android.synthetic.main.fragment_settings.view.*

/**
 * Provides logout feature. When logging out, all user data (token in sharedPref, orders saved in room database) will be removed.
 */
class SettingsFragment: Fragment() {

    private val viewModel by viewModels<SettingsViewModel> { getViewModelFactory(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        setOnClick(view)
        setObservers()
        return view
    }

    private fun setObservers() {
        viewModel.logout.observe(viewLifecycleOwner){isLoggedOut -> if (isLoggedOut) logout() }
    }

    private fun setOnClick(view: View) { view.btn_logout.setOnClickListener { viewModel.logout() } }

    private fun logout(){ findNavController().navigate(R.id.action_settingsFragment_to_loginFragment) }
}