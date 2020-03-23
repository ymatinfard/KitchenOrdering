package com.matinfard.kitchenordering.adapter

import android.view.View
import com.matinfard.kitchenordering.model.OrderEntity
import kotlinx.android.synthetic.main.order_layout.view.*

/**
 * Binds order data with view. if item clicked, onClickListItem is triggered.
 */
class OrderViewHolder(val view: View) : BaseViewHolder<OrderEntity>(view) {
    override fun bind(
        itemListener: KitchenListItemListener,
        list: List<OrderEntity>,
        position: Int
    ) {
        view.tv_order_date_time.text = list[position].dateTime
        view.tv_order_id.text = list[position].orderId.toString()
        view.tv_order_count.text = list[position].count.toString()
        view.tv_order_item_total_price.text = list[position].totalPrice.toString()
        view.layout_order.setOnClickListener {
            itemListener.onClickListItem(list[position].orderId)
        }
    }
}
