package com.matinfard.kitchenordering.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.matinfard.kitchenordering.R
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

/**
 * Application dashboard page. The first page that user sees after log in.
 */
class DashboardFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.fragment_dashboard

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClicks()
    }

    private fun setOnClicks() {
        mView.img_view_list.setOnClickListener { findNavController().navigate(R.id.action_dashboardFragment_to_viewListFragment) }
        mView.img_order.setOnClickListener { findNavController().navigate(R.id.action_dashboardFragment_to_orderFragment) }
        mView.img_settings.setOnClickListener { findNavController().navigate(R.id.action_dashboardFragment_to_settingsFragment) }
    }

}