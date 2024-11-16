package com.dicoding.mysubmission.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mysubmission.data.remote.Repository
import com.dicoding.mysubmission.data.remote.response.RegisterResponse
import kotlinx.coroutines.launch
import com.dicoding.mysubmission.data.Result

class RegisterViewModel(private val repository: Repository): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _registerResult = MutableLiveData<Result<RegisterResponse>>()
    val registerResult: LiveData<Result<RegisterResponse>> = _registerResult

    fun register(name: String, email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.register(name, email, password)
            _registerResult.value = result
            _isLoading.value = false
        }
    }

}