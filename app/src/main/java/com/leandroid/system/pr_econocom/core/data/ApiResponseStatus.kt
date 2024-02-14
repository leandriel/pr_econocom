package com.leandroid.system.pr_econocom.core.data

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseData<T>(
    val data: T? = null
)