package com.raj.mvvm_coroutines_retrofit.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raj.mvvm_coroutines_retrofit.data.model.User
import com.raj.mvvm_coroutines_retrofit.data.repository.MainRepository
import com.raj.mvvm_coroutines_retrofit.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val userLocalObject = MutableLiveData<Resource<ArrayList<User>>>()

    fun requestUser() {
        viewModelScope.launch {
            try {
                userLocalObject.postValue(Resource.success(ArrayList(mainRepository.getUsers())))
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun getUsersValues(): LiveData<Resource<ArrayList<User>>> {
        return userLocalObject
    }
}