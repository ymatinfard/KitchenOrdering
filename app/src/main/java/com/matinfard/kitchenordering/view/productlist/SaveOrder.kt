package com.matinfard.kitchenordering.view.productlist

import com.matinfard.kitchenordering.core.interactor.UseCase
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result.*
import com.matinfard.kitchenordering.data.model.OrderEntity
import com.matinfard.kitchenordering.core.interactor.UseCase.None
import com.matinfard.kitchenordering.view.productlist.SaveOrder.Params
import com.matinfard.kitchenordering.core.functional.Result

class SaveOrder (private val repository: Repository): UseCase<None, Params>() {

    override suspend fun run(params: Params): Result<Failure, None> {
        repository.saveOrder(params.order)
        return Success(None())
    }

    data class Params(val order: OrderEntity)
}