package com.raj.mvvm_coroutines_retrofit.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raj.mvvm_coroutines_retrofit.data.api.ApiHelper
import com.raj.mvvm_coroutines_retrofit.data.repository.MainRepository
import com.raj.mvvm_coroutines_retrofit.ui.main.viewmodel.MainViewModel

class ViewModelFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}