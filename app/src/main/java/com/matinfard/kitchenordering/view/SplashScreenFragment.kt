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
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.viewmodel.SplashScreenViewModel
import org.koin.android.ext.android.inject



/**
 * Shows company logo for 2 seconds and redirects user to login or dashboard page based on user login data. If user already has
 * logged in and there is token info in shared-preferences then user would be redirected to Dashboard page,otherwise login page.
 */
class SplashScreenFragment: Fragment() {

  //  private val viewModel by viewModels<SplashScreenViewModel> { getViewModelFactory(requireActivity()) }
    private val viewModel: SplashScreenViewModel by inject()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splashscreen, container, false)
        setObserver()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun setObserver() {
        viewModel.isUserAlreadyLoggedIn.observe(viewLifecycleOwner){ loginStatus ->
            if (loginStatus) navController.navigate(R.id.action_splashScreenFragmnt_to_dashboardFragment)
            else navController.navigate(R.id.action_splashScreenFragmnt_to_loginFragment)
        }
    }


}