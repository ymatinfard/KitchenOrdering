package com.matinfard.kitchenordering.view.orderitems

import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result
import com.matinfard.kitchenordering.core.interactor.UseCase
import com.matinfard.kitchenordering.data.model.OrderItemsEntity

class GetOrderItems(val repository: Repository): UseCase<List<OrderItemsEntity>, GetOrderItems.Params>() {

    override suspend fun run(params: Params): Result<Failure, List<OrderItemsEntity>> =
        repository.getAllOrderItems(params.orderId)

    data class Params(val orderId: Int)

}