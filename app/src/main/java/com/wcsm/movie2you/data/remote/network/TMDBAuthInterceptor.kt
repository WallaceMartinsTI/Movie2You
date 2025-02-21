package com.wcsm.movie2you.data.remote.network

import com.wcsm.movie2you.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class TMDBAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val request = requestBuilder.addHeader(
            "accept", "application/json"
        ).addHeader(
            "Authorization", Constants.BEARER_AUTHORIZATION
        ).build()

        return chain.proceed(request)
    }
}