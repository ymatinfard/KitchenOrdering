package com.matinfard.kitchenordering.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * This provides a shared interface among all viewholders
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(
        productItemListener: KitchenListItemListener,
        list: List<T>,
        position: Int
    )
}