package com.matinfard.kitchenordering.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.data.model.OrderEntity
import com.matinfard.kitchenordering.data.model.OrderItemsEntity
import com.matinfard.kitchenordering.data.model.Product
import com.matinfard.kitchenordering.view.orderitems.OrderItemsViewHolder
import com.matinfard.kitchenordering.view.orderlist.OrderViewHolder
import com.matinfard.kitchenordering.view.productlist.ProductViewHolder

/**
 * This is a base adapter for other three adapters aimed at reducing repetition
 */
class KitchenDataAdapter :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var adapterDataList: List<Any> = listOf()

    internal var clickListener: (Int) -> Unit = { _ -> }

    fun submitList(newList: List<Any>) {
        adapterDataList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            Constants.TYPE_PRODUCT_LIST -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.product_item_layout, parent, false)
                ProductViewHolder(
                    view
                )
            }
            Constants.TYPE_PRODUCT_ORDER_ITEMS -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.order_item_layout, parent, false)
                return OrderItemsViewHolder(
                    view
                )

            }
            Constants.TYPE_PRODUCT_ORDER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.order_layout, parent, false)
                OrderViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = adapterDataList.size


    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ProductViewHolder -> holder.bind(
                clickListener,
                adapterDataList as List<Product>,
                position
            )
            is OrderItemsViewHolder -> holder.bind(
                clickListener,
                adapterDataList as List<OrderItemsEntity>,
                position
            )
            is OrderViewHolder -> holder.bind(
                clickListener,
                adapterDataList as List<OrderEntity>,
                position
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterDataList[position]) {
            is Product -> Constants.TYPE_PRODUCT_LIST
            is OrderItemsEntity -> Constants.TYPE_PRODUCT_ORDER_ITEMS
            is OrderEntity -> Constants.TYPE_PRODUCT_ORDER
            else -> throw IllegalArgumentException("Invalid type of data ")
        }
        return super.getItemViewType(position)

    }
}

