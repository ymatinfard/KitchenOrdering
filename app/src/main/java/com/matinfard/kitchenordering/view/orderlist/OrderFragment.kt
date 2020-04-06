package com.matinfard.kitchenordering.view.orderlist

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.utils.KitchenDataAdapter
import com.matinfard.kitchenordering.utils.Constants
import com.matinfard.kitchenordering.view.BaseFragment
import com.matinfard.kitchenordering.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.fragment_order_items.view.*
import org.koin.android.ext.android.inject

/**
 * Shows all created orders which already saved in local storage
 */
class OrderFragment: BaseFragment() {

    private val mViewModel: OrderViewModel by inject()

    private val mAdapter: KitchenDataAdapter by inject()

    override fun layoutId(): Int = R.layout.fragment_order_items

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setObservers()
    }

    private fun setObservers() {
        mViewModel.ordersList.observe(viewLifecycleOwner) { orderList ->
            mAdapter.submitList(orderList)
        }
    }

    private fun initRecyclerView() {
        mAdapter.clickListener = onItemClicked
        mView.recyclerview_order.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
    private val onItemClicked: (orderId: Int) -> Unit = {orderId ->
        val bundle = bundleOf(Constants.ORDER_ID to orderId)
        findNavController().navigate(R.id.action_orderFragment_to_orderItemsFragment, bundle)
    }
}