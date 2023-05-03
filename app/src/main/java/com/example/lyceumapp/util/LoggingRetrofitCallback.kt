package com.example.lyceumapp.util

import android.util.Log
import com.example.lyceumapp.LOG_TAG_RETROFIT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoggingRetrofitCallback<T>(private val onResponseListener: (Call<T>, Response<T>) -> Unit,
private val onFailureListener: () -> Unit): Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        val msg = """
            onResponse():
            code: ${response.code()}
            url: ${response.raw().request().url()}
            body: ${response.body()}
        """.trimIndent()
        Log.d(LOG_TAG_RETROFIT, msg)
        onResponseListener(call, response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        val msg = """
            onFailure()
            ${t.message}
        """.trimIndent()
        Log.d(LOG_TAG_RETROFIT, msg)
        onFailureListener
    }
}