package com.matinfard.kitchenordering.adapter

/**
 *  A listener class to handle onClick events on List. Other listener methods could be added in the future development
 */
class KitchenListItemListener(val onClickListItem: (Int) -> Unit) {
    fun itemClicked(itemId: Int) = onClickListItem(itemId)
}
