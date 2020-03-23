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
import com.matinfard.kitchenordering.utils.progressbarVisibilityStatus
import com.matinfard.kitchenordering.utils.showErrorMessage
import com.matinfard.kitchenordering.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

/**
 * Login page. User needs to enter a valid username and password to log in. Username should be in an email format, otherwise log-in would fail.
 */
class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel> { getViewModelFactory(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        setOnClickListener(view)
        setObservers(view)
        return view
    }

    private fun setOnClickListener(view: View) {
        view.btn_login.setOnClickListener {
                viewModel.validateUser(et_username.text.toString(), et_password.text.toString()) }
    }

    private fun setObservers(view: View) {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressbarVisibilityStatus(view, isLoading)
        }
        viewModel.isUserLoginSuccessful.observe(viewLifecycleOwner) { isSuccessful ->
           if (isSuccessful) findNavController().navigate(R.id.action_viewListFragment_to_dashboardFragment) else showErrorMessage(view)
        }
    }
}


