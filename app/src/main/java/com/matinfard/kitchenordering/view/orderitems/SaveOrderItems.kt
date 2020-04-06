package com.matinfard.kitchenordering.view.orderitems

import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.view.orderitems.SaveOrderItems.Params
import com.matinfard.kitchenordering.core.functional.Result
import com.matinfard.kitchenordering.core.functional.Result.*
import com.matinfard.kitchenordering.core.interactor.UseCase
import com.matinfard.kitchenordering.core.interactor.UseCase.None
import com.matinfard.kitchenordering.data.model.OrderItemsEntity


class SaveOrderItems(private val repository: Repository) : UseCase<None, Params>() {

    override suspend fun run(params: Params): Result<Failure, None> {
            repository.saveOrderItems(params.orderItems)
        return Success(None())
    }

    data class Params(val orderItems: List<OrderItemsEntity>)
}