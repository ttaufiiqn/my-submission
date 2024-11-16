package com.dicoding.mysubmission.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mysubmission.data.remote.Repository
import com.dicoding.mysubmission.di.Injection
import com.dicoding.mysubmission.ui.viewmodel.RegisterViewModel


class RegisterFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: RegisterFactory? = null
        fun getInstance(): RegisterFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RegisterFactory(
                    Injection.repository()
                )
            }.also { INSTANCE = it }
    }
}