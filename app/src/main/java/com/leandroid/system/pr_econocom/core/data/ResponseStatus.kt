package com.leandroid.system.pr_econocom.core.data

sealed interface ResponseStatus<out A> {
    data class Loading(val loading: Boolean): ResponseStatus<Nothing>
    data class Success<out A>(val data: A): ResponseStatus<A>
    data class Failure(val cause: Exception ): ResponseStatus<Nothing>
}