package com.matinfard.kitchenordering.data

import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result
import retrofit2.Response

open class BaseDataSource {

    suspend fun <T : Any, R> request(
        call: suspend () -> Response<T>,
        transform: (T) -> R,
        default: T
    ): Result<Failure, R> {
        return try {
            val response = call.invoke()
            when (response.isSuccessful) {
                true -> Result.Success(transform((response.body() ?: default)))
                false -> Result.Failure(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Result.Failure(Failure.ServerError)
        }
    }

    suspend fun <T> request(
        call: suspend () -> (T),
        default: T
    ): Result<Failure, T> {
        return try {
            val response = call.invoke()
            Result.Success(response ?: default)
        } catch (exception: Throwable) {
            Result.Failure(Failure.ServerError)
        }
    }
}