package com.leandroid.system.pr_econocom.core.domain

interface BaseUseCase<Param, Resp> {
    suspend fun execute(param: Param): Resp
}