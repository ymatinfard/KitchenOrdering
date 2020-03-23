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
class DashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        setOnClicks(view)
        return view
    }

    private fun setOnClicks(view: View) {
        view.img_view_list.setOnClickListener { findNavController().navigate(R.id.action_dashboardFragment_to_viewListFragment) }
        view.img_order.setOnClickListener { findNavController().navigate(R.id.action_dashboardFragment_to_orderFragment) }
        view.img_settings.setOnClickListener { findNavController().navigate(R.id.action_dashboardFragment_to_settingsFragment) }
    }

}