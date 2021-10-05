package com.raj.mvvm_coroutines_retrofit.data.api

import com.raj.mvvm_coroutines_retrofit.data.model.User

class ApiServiceImpl : ApiService {
    private val apiService = RetrofitInstance.createService(ApiService::class.java)
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}