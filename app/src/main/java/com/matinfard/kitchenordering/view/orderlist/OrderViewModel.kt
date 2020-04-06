package com.matinfard.kitchenordering.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.view.orderlist.GetAllOrders
import com.matinfard.kitchenordering.core.interactor.UseCase
import com.matinfard.kitchenordering.data.model.OrderEntity
import kotlinx.coroutines.launch

class OrderViewModel(private val getAllOrders: GetAllOrders): ViewModel() {

    private val _ordersList = MutableLiveData<List<OrderEntity>>()
    val ordersList : LiveData<List<OrderEntity>> = _ordersList

    init {
        viewModelScope.launch {
            loadOrders()
        }
    }

    private suspend fun loadOrders() {
        getAllOrders(UseCase.None()){it.fold(::handleFailure, ::handleOrdersList)}
    }

    private fun handleOrdersList(orders: List<OrderEntity>) {
            _ordersList.value = orders
    }

    private fun handleFailure(failure: Failure) {
        _ordersList.value = emptyList()
    }
}