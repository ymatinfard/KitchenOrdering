package com.matinfard.kitchenordering.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.viewmodel.SplashScreenViewModel
import org.koin.android.ext.android.inject



/**
 * Shows company logo for 2 seconds and redirects user to login or dashboard page based on user login data. If user already has
 * logged in and there is token info in shared-preferences then user would be redirected to Dashboard page,otherwise login page.
 */
class SplashScreenFragment: BaseFragment() {

    private val mViewModel: SplashScreenViewModel by inject()

    override fun layoutId() = R.layout.fragment_splashscreen

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        mViewModel.isUserAlreadyLoggedIn.observe(viewLifecycleOwner){ loginStatus ->
            if (loginStatus) findNavController().navigate(R.id.action_splashScreenFragmnt_to_dashboardFragment)
            else findNavController().navigate(R.id.action_splashScreenFragmnt_to_loginFragment)
        }
    }


}