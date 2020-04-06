package com.matinfard.kitchenordering.view.orderitems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.data.model.OrderItemsEntity
import kotlinx.coroutines.launch

class OrderItemsViewModel(val getOrderItems: GetOrderItems) : ViewModel() {

    private val _orderItemsList = MutableLiveData<List<OrderItemsEntity>>()
    val orderItemsList: LiveData<List<OrderItemsEntity>> = _orderItemsList

    private val _orderItemsTotalPrice = MutableLiveData<Int>()
    val orderItemsTotalPrice: LiveData<Int> = _orderItemsTotalPrice

     fun loadOrderItems(orderId: Int) {
         viewModelScope.launch {
             getOrderItems(
                 GetOrderItems.Params(
                     orderId
                 )
             ) {
                 it.fold(::handleFailure, ::handleOrderItemsList)
             }
         }
    }

    private fun handleOrderItemsList(orderItems: List<OrderItemsEntity>) {
        _orderItemsList.value = orderItems
        _orderItemsTotalPrice.value = orderItems.map{it.price}.sum()
    }

    private fun handleFailure(failure: Failure) {
        _orderItemsList.value = emptyList()
    }
}