package com.matinfard.kitchenordering.adapter

import android.view.View
import com.matinfard.kitchenordering.model.OrderItemsEntity
import kotlinx.android.synthetic.main.order_item_layout.view.*

/**
 *  photo loading line of code is commented to show a beautiful default  icon. This line could be uncommented to see server provided photos.
 */

class OrderItemsViewHolder(val view: View) : BaseViewHolder<OrderItemsEntity>(view) {

    override fun bind(
        listItemListener: KitchenListItemListener,
        list: List<OrderItemsEntity>,
        position: Int
    ) {
        view.tv_order_item_name.text = list[position].name
        view.tv_order_item_price.text = list[position].price.toString()
        // Below code commented to show a better image on User Interface
        //   Picasso.get().load(item.picture).placeholder(R.drawable.ic_photo_place_holder).error(R.drawable.ic_broken_image).into(view.img_order_item)
    }
}
