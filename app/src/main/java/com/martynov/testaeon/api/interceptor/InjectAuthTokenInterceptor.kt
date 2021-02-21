package com.martynov.testaeon.api.interceptor

import com.martynov.testaeon.AUTH_TOKEN_HEADER
import okhttp3.Interceptor
import okhttp3.Response

class InjectAuthTokenInterceptor(val authToken: () -> String?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithToken = originalRequest.newBuilder()
            .addHeader("app-key", "12345 ")
            .addHeader("v", "1")
            .build()
        return chain.proceed(requestWithToken)
    }
}