package com.matinfard.kitchenordering.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.adapter.KitchenDataAdapter
import com.matinfard.kitchenordering.adapter.KitchenListItemListener
import com.matinfard.kitchenordering.utils.dataIsLoading
import com.matinfard.kitchenordering.utils.getViewModelFactory
import com.matinfard.kitchenordering.viewmodel.ViewListSharedViewModel
import kotlinx.android.synthetic.main.fragment_view_list.view.*

/**
 * Shows product list fetched from server. User can add multiple products to cart and make an order.
 */
class ViewListFragment : Fragment() {

private val viewModel by viewModels<ViewListSharedViewModel> { getViewModelFactory(requireActivity()) }
    private lateinit var productAdapter: KitchenDataAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_list, container, false)

        setProductRecyclerViewAdapter(view)
        setObservers(view)
        setOnclick(view)
        return view
    }

    private fun setOnclick(view: View) {
        view.btn_order.setOnClickListener{
            viewModel.saveOrders() }
    }

    private fun setObservers(view: View) {
        viewModel.productList.observe(viewLifecycleOwner) { productList ->
            productAdapter.submitList(productList)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading -> dataIsLoading(view, isLoading) }

        viewModel.ordersCount.observe(viewLifecycleOwner){ orderCountTmp ->
            orderCountTmp?.let{view.tv_order_count_lable.text = it.toString()}
        }
        viewModel.userMessage.observe(viewLifecycleOwner){messge ->
            Toast.makeText(context, messge, Toast.LENGTH_SHORT).show()
        }
        viewModel.navigateToOrderFragment().observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_viewListFragment_to_orderFragment)
        }
    }

    private fun setProductRecyclerViewAdapter(view: View) {
        productAdapter = KitchenDataAdapter(KitchenListItemListener(onItemClicked))
        view.recyclerview_product.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private val onItemClicked: (productId: Int) -> Unit = { id ->
        viewModel.addOrderToCart(id)
    }

}