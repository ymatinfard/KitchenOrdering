package com.matinfard.kitchenordering.view.productlist

import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result
import com.matinfard.kitchenordering.core.interactor.UseCase
import com.matinfard.kitchenordering.data.model.Product

class GetProducts (private val repository: Repository): UseCase<List<Product>, GetProducts.Params>() {

    override suspend fun run(params: Params): Result<Failure, List<Product>> = repository.getProducts(params.token)

    data class Params(val token: String)
}