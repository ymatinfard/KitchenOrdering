package com.matinfard.kitchenordering.view.productlist

import android.util.Log
import androidx.lifecycle.*
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.view.productlist.GetProducts.Params
import com.matinfard.kitchenordering.data.model.OrderEntity
import com.matinfard.kitchenordering.data.model.OrderItemsEntity
import com.matinfard.kitchenordering.data.model.Product
import com.matinfard.kitchenordering.utils.SharedPrefToken
import com.matinfard.kitchenordering.utils.SingleLiveEvent
import com.matinfard.kitchenordering.view.orderitems.SaveOrderItems
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 */
class ProductListViewModel(
    private val getProducts: GetProducts
    , private val saveOrder: SaveOrder
    , private val saveOrderItems: SaveOrderItems
) :
    ViewModel(), KoinComponent {

    private val _productListLiveData = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productListLiveData

    private var _userMessage = MutableLiveData<String>()
    val userMessage: LiveData<String> = _userMessage

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private var _ordersCountTmp = MutableLiveData<Int?>()
    val ordersCount: LiveData<Int?> = _ordersCountTmp

    private val _navigateToOrderFragment = SingleLiveEvent<Any>()
    val navigateToOrderFragment: LiveData<Any> = _navigateToOrderFragment

    private val sharedPrefToken: SharedPrefToken by inject()

    private var orderListTemp = mutableListOf<Product>()

    private var productListResult: List<Product>? = emptyList()

    init {
        viewModelScope.launch { getProducts() }
    }

    private suspend fun getProducts() =
        getProducts(Params("5e8c3c48-af49-425b-a6d9-f37f3511e4fa")) {
            it.fold(::handleFailure, ::handleProductList)
        }

    private suspend fun saveOrder(order: OrderEntity){
        saveOrder(SaveOrder.Params(order))
    }

    private suspend fun saveOrderItems(orderItems: List<OrderItemsEntity>){
        saveOrderItems(
            SaveOrderItems.Params(
                orderItems
            )
        )
    }

    private fun handleProductList(productList: List<Product>) {
        productListResult = productList
        _productListLiveData.value = productList
        Log.d("Yousef", productList.toString())
    }

    private fun handleFailure(failure: Failure) {
        Log.d("Yousef", "Error")
        _userMessage.value = "error..."
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
                saveOrder(order)
                saveOrderItems(orderListTemp.map {
                    OrderItemsEntity(
                        0,
                        order.orderId,
                        it.guid,
                        it.name,
                        it.price,
                        it.picture
                    )
                })

                productListResult?.map {
                    it.selected = false
                }.also {
                    _productListLiveData.value = productListResult
                    orderListTemp.clear()
                    _ordersCountTmp.postValue(0) }

                _navigateToOrderFragment.call(true)
            }
        } else {
            _userMessage.value = "Please select an Item to make an Order!"
        }
    }

    private fun createOrder(): OrderEntity {
        val orderId = Random().nextInt(100000 - 1000) + 1000
        val currentDate = SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(Date())
        val count = orderListTemp.size
        val orderListTotalPrice = orderListTemp.toMutableList()
        val totalPrice = orderListTotalPrice.map { it.price }.sum()
        return OrderEntity(orderId, currentDate, count, totalPrice)
    }

}



