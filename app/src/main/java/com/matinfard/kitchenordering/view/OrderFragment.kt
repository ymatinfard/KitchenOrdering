package com.matinfard.kitchenordering.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.adapter.KitchenDataAdapter
import com.matinfard.kitchenordering.adapter.KitchenListItemListener
import com.matinfard.kitchenordering.utils.Constants
import com.matinfard.kitchenordering.utils.getViewModelFactory
import com.matinfard.kitchenordering.viewmodel.ViewListSharedViewModel
import kotlinx.android.synthetic.main.fragment_order_items.view.*

/**
 * Shows all created orders which already saved in local storage
 */
class OrderFragment: Fragment() {

    private val viewModel by viewModels<ViewListSharedViewModel> { getViewModelFactory(requireActivity()) }

    private lateinit var orderAdapter: KitchenDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        initRecyclerView(view)
        setObservers()
        return view
    }

    private fun setObservers() {
        viewModel.getAllOrders().observe(viewLifecycleOwner){ order -> orderAdapter.submitList(order) } }


    private fun initRecyclerView(view : View) {
        orderAdapter = KitchenDataAdapter(KitchenListItemListener(onItemClicked))
        view.recyclerview_order.apply {
            adapter = orderAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
    private val onItemClicked: (orderId: Int) -> Unit = {orderId ->
        val bundle = bundleOf(Constants.ORDER_ID to orderId)
        findNavController().navigate(R.id.action_orderFragment_to_orderItemsFragment, bundle)
    }
}