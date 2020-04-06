package com.wajahatkarim3.mvvm.gdglive.ktx

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallback<T>(initMethod: RetrofitCallback<T>.() -> Unit) : Callback<T> {

    private var _failureCallback: (call: Call<T>?, throwable: Throwable?) -> Unit = {_, _ -> }
    private var _successCallback: (call: Call<T>?, response: Response<T>?) -> Unit = {_, _ -> }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        _successCallback(call, response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        _failureCallback(call, t)
    }

    fun onSuccess (callback: (call: Call<T>?, response: Response<T>?) -> Unit) {
        _successCallback = callback
    }

    fun onError(callback: (call: Call<T>?, throwable: Throwable?) -> Unit) {
        _failureCallback = callback
    }

    init {
        initMethod()
    }
}