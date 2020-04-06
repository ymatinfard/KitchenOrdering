package com.matinfard.kitchenordering.view.productlist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.utils.KitchenDataAdapter
import com.matinfard.kitchenordering.data.model.Product

import com.matinfard.kitchenordering.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_view_list.view.*
import org.koin.android.ext.android.inject

/**
 * Shows product list fetched from server. User can add multiple products to cart and make an order.
 */
class ProductListFragment : BaseFragment() {

    private val mViewModel: ProductListViewModel by inject()

    private val mAdapter: KitchenDataAdapter by inject()

    override fun layoutId(): Int = R.layout.fragment_view_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProductRecyclerViewAdapter()
        setObservers()
        setOnclick()
    }

    private fun setOnclick() {
        mView.btn_order.setOnClickListener { mViewModel.saveOrders() }
    }

    private fun handleProductAdapter(productList: List<Product>) {
        mAdapter.submitList(productList)
    }

    private fun handleUserMessage(message: String) {
        Snackbar.make(mView.view_list_root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun handleNavigation(event: Any) {
        findNavController().navigate(R.id.action_viewListFragment_to_orderFragment)
    }

    private fun handleOrdersCount(orderCount: Int?){
        orderCount?.let{mView.tv_order_count_lable.text = it.toString()}
    }

    private val onItemClicked: (productId: Int) -> Unit = { id ->
        mViewModel.addOrderToCart(id)
    }

    private fun setObservers() {
        mViewModel.apply {
            productList.observe(viewLifecycleOwner, ::handleProductAdapter)
            userMessage.observe(viewLifecycleOwner, ::handleUserMessage)
            ordersCount.observe(viewLifecycleOwner, ::handleOrdersCount)
            navigateToOrderFragment.observe(viewLifecycleOwner, ::handleNavigation)
        }
    }

    private fun setProductRecyclerViewAdapter() {
        mAdapter.clickListener = onItemClicked
        mView.recyclerview_product.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}