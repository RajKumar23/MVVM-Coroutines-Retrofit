package com.raj.mvvm_coroutines_retrofit.data.api

import com.raj.mvvm_coroutines_retrofit.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val baseURL = "https://5e510330f2c0d300147c034c.mockapi.io/"

    private val okHttpClient = if (BuildConfig.DEBUG) {
        OkHttpClient.Builder()
            .callTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .hostnameVerifier { _, _ -> true }
            .build()
    } else OkHttpClient
        .Builder()
        .callTimeout(100, TimeUnit.SECONDS)
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .hostnameVerifier { _, _ -> true }
        .build()

    private val builder = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(OkHttpClient()).build()
        return retrofit.create(serviceClass)
    }

}