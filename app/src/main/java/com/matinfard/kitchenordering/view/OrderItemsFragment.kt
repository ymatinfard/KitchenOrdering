package com.matinfard.kitchenordering.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.adapter.KitchenDataAdapter
import com.matinfard.kitchenordering.adapter.KitchenListItemListener
import com.matinfard.kitchenordering.utils.Constants
import com.matinfard.kitchenordering.utils.getViewModelFactory
import com.matinfard.kitchenordering.viewmodel.ViewListSharedViewModel
import kotlinx.android.synthetic.main.fragment_view_list.view.*
import kotlinx.android.synthetic.main.fragment_order_items.view.*
import timber.log.Timber

/**
 * Shows all items of selected order.
 */
class OrderItemsFragment : Fragment() {

    private val viewModel by viewModels<ViewListSharedViewModel> { getViewModelFactory(requireActivity())}

    private lateinit var orderAdapter: KitchenDataAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_items, container, false)
        initRecyclerView(view)
        setObservers(view)
        viewModel.setOrderid(requireArguments().getInt(Constants.ORDER_ID))
        return view
    }

    private fun setObservers(view: View) {
        viewModel.orderItems.observe(viewLifecycleOwner) { orderItems ->
            orderAdapter.submitList(orderItems)
            Timber.d(orderItems.toString())
        }
        viewModel.orderItemsTotalPrice.observe(viewLifecycleOwner) { totalPrice ->
            totalPrice?.let { view.tv_order_item_total_price.text = it.toString() }
                ?: run { view.tv_order_item_total_price.text = Constants.ZERO }
        }
        viewModel.ordersCount.observe(viewLifecycleOwner) { count ->
            view.tv_order_count_lable.text = count.toString()
        }
    }

    private fun initRecyclerView(view: View) {
        orderAdapter = KitchenDataAdapter(KitchenListItemListener(onItemClicked))
        view.recyclerview_order.apply {
            adapter = orderAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
    // for deleting an order
    private val onItemClicked: (orderId: Int) -> Unit = { }
}