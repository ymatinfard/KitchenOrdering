package com.matinfard.kitchenordering.view.orderitems

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.utils.KitchenDataAdapter
import com.matinfard.kitchenordering.data.model.OrderItemsEntity
import com.matinfard.kitchenordering.utils.Constants
import com.matinfard.kitchenordering.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_order_items.view.*
import kotlinx.android.synthetic.main.fragment_order_items.view.tv_order_item_total_price
import org.koin.android.ext.android.inject

/**
 * Shows all items of selected order.
 */
class OrderItemsFragment : BaseFragment() {

    private val mViewModel: OrderItemsViewModel by inject()

    private val mAdapter: KitchenDataAdapter by inject()

    override fun layoutId(): Int = R.layout.order_item_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setObservers()
        mViewModel.loadOrderItems(requireArguments().getInt(Constants.ORDER_ID))
    }

    private fun setObservers() {
        mViewModel.apply {
            orderItemsList.observe(viewLifecycleOwner, ::handleAdapter)
            orderItemsTotalPrice.observe(viewLifecycleOwner, ::handleOrderItemsTotalPrice)
        }
    }

    private fun handleAdapter(orderItems: List<OrderItemsEntity>){
        mAdapter.submitList(orderItems)
    }

    private fun handleOrderItemsTotalPrice(totalPrice: Int){
        mView.tv_order_item_total_price.text = totalPrice.toString()
    }

    private fun initRecyclerView() {
        mAdapter.clickListener = onItemClicked
        mView.recyclerview_order.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
    // for deleting an order
    private val onItemClicked: (orderId: Int) -> Unit = { }
}