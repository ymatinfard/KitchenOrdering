package com.matinfard.kitchenordering.view.settings

import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result
import com.matinfard.kitchenordering.core.interactor.UseCase
import com.matinfard.kitchenordering.core.interactor.UseCase.None

class RemoveDatabaseInfo(private val repository: Repository): UseCase<None, None>() {
    override suspend fun run(params: None): Result<Failure, None> {
        repository.removeDatabaseInfo()
        return Result.Success(None())
    }
}