package com.matinfard.kitchenordering.core.interactor

import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result
import kotlinx.coroutines.*

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Result<Failure, Type>

    suspend operator fun invoke(params: Params, onResult: (Result<Failure, Type>) -> Unit = {}) {
        coroutineScope {
            val job = async(Dispatchers.IO) { run(params) }
            withContext(Dispatchers.Main) { onResult(job.await()) }
        }

    }

    class None
}
