package ru.adanil.weather.core.gateway.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset

class HttpLoggingInterceptor : Interceptor {
    companion object {
        private val TAG = HttpLoggingInterceptor::class.simpleName
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.d(TAG, request.toString())
        if (request.body != null && Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, bodyToString(request))
        }

        val response = chain.proceed(request)
        val responseBody = response.body

        if (responseBody != null) {
            val source = responseBody.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.

            Log.d(TAG, source.buffer.clone().readString(Charset.defaultCharset()))
        }

        return response
    }

    private fun bodyToString(request: Request): String {
        return try {
            val copy: Request = request.newBuilder().build()
            val buffer = Buffer()
            copy.body?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException) {
            ""
        }
    }
}
