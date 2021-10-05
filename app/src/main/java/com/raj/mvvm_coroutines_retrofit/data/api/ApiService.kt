package com.raj.mvvm_coroutines_retrofit.data.api

import com.raj.mvvm_coroutines_retrofit.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}