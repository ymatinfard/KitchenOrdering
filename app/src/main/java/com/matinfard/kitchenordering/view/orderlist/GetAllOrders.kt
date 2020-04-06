package com.matinfard.kitchenordering.view.orderlist


import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result
import com.matinfard.kitchenordering.core.interactor.UseCase
import com.matinfard.kitchenordering.data.model.OrderEntity

class GetAllOrders(private val repository: Repository): UseCase<List<OrderEntity>, UseCase.None>(){

    override suspend fun run(params: None): Result<Failure, List<OrderEntity>> =
         repository.getAllOrders()
}