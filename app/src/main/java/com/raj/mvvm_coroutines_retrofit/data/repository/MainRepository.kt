package com.raj.mvvm_coroutines_retrofit.data.repository

import com.raj.mvvm_coroutines_retrofit.data.api.ApiHelper
import com.raj.mvvm_coroutines_retrofit.data.model.User

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getUsers(): List<User> {
        return apiHelper.getUsers()
    }

}