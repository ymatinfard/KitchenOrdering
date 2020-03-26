package com.matinfard.kitchenordering.viewmodel

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.lifecycle.*
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.model.OrderEntity
import com.matinfard.kitchenordering.model.OrderItemsEntity
import com.matinfard.kitchenordering.model.Product
import com.matinfard.kitchenordering.utils.SharedPrefToken
import com.matinfard.kitchenordering.utils.SingleLiveEvent
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * Shared viewmodel among "order, orderItems, viewList" fragments.
 */
class ViewListSharedViewModel(private val repository: Repository) :
    ViewModel(), KoinComponent {

    private val orderSavedNavigate = SingleLiveEvent<Boolean>()

    private val _productListLiveData = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productListLiveData

    private var _userMessage = MutableLiveData<String>()
    val userMessage: LiveData<String> = _userMessage

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private var _ordersCountTmp = MutableLiveData<Int?>()
    val ordersCount: LiveData<Int?> = _ordersCountTmp

    private var _orderIdLiveData = MutableLiveData<Int>()

 //   private var sharedPrefToken = SharedPrefToken(context)
    private val sharedPrefToken: SharedPrefToken by inject()
    private var orderListTemp = mutableListOf<Product>()
    private var productListResult: List<Product>? = emptyList()

    val orderItems = _orderIdLiveData.switchMap {orderId ->
        liveData(Dispatchers.IO) {
            emit(repository.getAllOrderItems(orderId))
        }
    }

    val orderItemsTotalPrice = _orderIdLiveData.switchMap { orderId ->
        liveData(Dispatchers.IO) {
            emit(repository.getOrderItemsTotalPrice(orderId))
        }
    }

    init {
        viewModelScope.launch { getProducts() }
    }

    private suspend fun getProducts() {
        sharedPrefToken.userToken.also { isLoading(true) }?.let { userToken ->
            productListResult = viewModelScope.async(Dispatchers.IO) {
                repository.getProducts(userToken)
            }.await()
            checkProductListAndUpdateUI(productListResult)
        }.also { isLoading(false) }

    }

    private fun checkProductListAndUpdateUI(productListResult: List<Product>?) {
        productListResult?.let {
            _productListLiveData.value = productListResult
        } ?: run {
            _productListLiveData.value = emptyList()
        }
    }

    private fun isLoading(status: Boolean) {
        _isLoading.value = status
    }

    fun addOrderToCart(productId: Int) {
        val newOrder = productListResult!![productId]

        if (!orderListTemp.contains(newOrder)) {
            orderListTemp.add(newOrder)
            _ordersCountTmp.value = orderListTemp.size.also { Timber.d(it.toString()) }
        } else {
            _userMessage.value = "You already added this product!"
        }
    }

    fun saveOrders() {
        if (orderListTemp.size > 0) {

            viewModelScope.launch {
                val order = createOrder()
                repository.saveOrder(order)
                repository.saveOrderItems(orderListTemp.map {
                    OrderItemsEntity(
                        0,
                        order.orderId,
                        it.guid,
                        it.name,
                        it.price,
                        it.picture
                    )
                })

                orderSavedNavigate.value = true

                productListResult?.map {
                    it.selected = false
                }.also {
                    _productListLiveData.value = productListResult
                    orderListTemp.clear()
                    _ordersCountTmp.postValue(0) }
            }
        } else {
            _userMessage.value = "Please select an Item to make an Order!"
        }
    }

    fun getAllOrders(): LiveData<List<OrderEntity>> = repository.getAllOrders()

    private fun createOrder(): OrderEntity {
        val orderId = Random().nextInt(100000 - 1000) + 1000
        val currentDate = SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(Date())
        val count = orderListTemp.size
        val orderListTotalPrice = orderListTemp.toMutableList()
        val totalPrice = orderListTotalPrice.map { it.price }.sum()
        return OrderEntity(orderId, currentDate, count, totalPrice)
    }

    fun navigateToOrderFragment(): SingleLiveEvent<Boolean> {
        return orderSavedNavigate
    }

    fun setOrderid(orderId: Int){
        _orderIdLiveData.value = orderId
    }


}



