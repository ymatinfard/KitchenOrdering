package com.matinfard.kitchenordering.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * This provides a shared interface among all viewholders
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(
        productItemListener: (Int) -> Unit,
        list: List<T>,
        position: Int
    )
}