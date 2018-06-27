package com.seongenie.handycoin.collector.infra

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CollectorModule {
    fun <T> callbackWrapper(success: ((Response<T>) -> Unit)?, failure: ((t: Throwable) -> Unit)? = null): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) { success?.invoke(response) }
            override fun onFailure(call: Call<T>, t: Throwable) { failure?.invoke(t) }
        }
    }
}