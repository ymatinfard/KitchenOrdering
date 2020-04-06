package com.matinfard.kitchenordering.core.functional

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Result] are either an instance of [Failure] or [Success].
 * FP Convention dictates that [Failure] is used for "failure"
 * and [Success] is used for "success".
 *
 * @see Failure
 * @see Success
 */
sealed class Result<out L, out R> {
    /** * Represents the left side of [Result] class which by convention is a "Failure". */
    data class Failure<out L>(val a: L) : Result<L, Nothing>()

    /** * Represents the right side of [Result] class which by convention is a "Success". */
    data class Success<out R>(val b: R) : Result<Nothing, R>()

    /**
     * Returns true if this is a Right, false otherwise.
     * @see Success
     */
    val isSuccessful get() = this is Success<R>

    /**
     * Returns true if this is a Left, false otherwise.
     * @see Failure
     */
    val isFailure get() = this is Failure<L>

    /**
     * Creates a Left type.
     * @see Failure
     */
    fun <L> failure(a: L) = Failure(a)

    /**
     * Creates a Left type.
     * @see Success
     */
    fun <R> success(b: R) = Success(b)

    /**
     * Applies fnL if this is a failure or fnR if this is a success.
     * @see Failure
     * @see Success
     */
    fun fold(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Failure -> fnL(a)
            is Success -> fnR(b)
        }
}

/**
 * Composes 2 functions
 * See <a href="https://proandroiddev.com/kotlins-nothing-type-946de7d464fb">Credits to Alex Hart.</a>
 */
fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

/**
 * Right-biased flatMap() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
fun <T, L, R> Result<L, R>.flatMap(fn: (R) -> Result<L, T>): Result<L, T> =
    when (this) {
        is Result.Failure -> Result.Failure(a)
        is Result.Success -> fn(b)
    }

/**
 * Right-biased map() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
fun <T, L, R> Result<L, R>.map(fn: (R) -> (T)): Result<L, T> = this.flatMap(fn.c(::success))

/** Returns the value from this `Right` or the given argument if this is a `Left`.
 *  Right(12).getOrElse(17) RETURNS 12 and Left(12).getOrElse(17) RETURNS 17
 */
fun <L, R> Result<L, R>.getOrElse(value: R): R =
    when (this) {
        is Result.Failure -> value
        is Result.Success -> b
    }
