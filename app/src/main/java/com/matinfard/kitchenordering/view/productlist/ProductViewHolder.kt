package com.matinfard.kitchenordering.adapter

import android.view.View
import com.matinfard.kitchenordering.data.model.Product
import kotlinx.android.synthetic.main.product_item_layout.view.*

/**
 * Binds products list data with view. if "add to cart" button clicked, the "selected" property of "productList" is set to true to keep
 * track of selected items view during scrolling
 */

class ProductViewHolder(val view: View): BaseViewHolder<Product>(view) {
   override fun bind(productItemListener: (Int) -> Unit,
                     productList: List<Product>,
                     position: Int
    ) {
        view.tv_product_name.text = productList[position].name
       // It is commented just for test and having a good looking icon on List
        //      Picasso.get().load(productList.get(position).picture).placeholder(R.drawable.ic_photo_place_holder).error(R.drawable.ic_broken_image).into(view.img_product)

        view.img_product_selected.visibility =
            if (productList[position].selected) View.VISIBLE else View.GONE

        view.tv_product_price.text = productList[position].price.toString()

        view.btn_add_to_cart.setOnClickListener {
            productList[position].selected = true
            view.img_product_selected.visibility = View.VISIBLE
            productItemListener(position)
        }
    }
}