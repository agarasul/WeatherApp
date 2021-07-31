package dev.rasul.weatherapp.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request()

        val currentUrl = currentRequest.url.newBuilder()

        currentUrl
            .addQueryParameter("appid", "c95691c9def14843df725133fbee0e7b")
            .addQueryParameter("lang", "en")

        return chain.proceed(currentRequest.newBuilder().url(currentUrl.build()).build())
    }
}