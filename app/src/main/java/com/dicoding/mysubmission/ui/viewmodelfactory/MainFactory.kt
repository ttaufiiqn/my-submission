package com.dicoding.mysubmission.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mysubmission.data.remote.Repository
import com.dicoding.mysubmission.di.Injection
import com.dicoding.mysubmission.ui.viewmodel.MainViewModel


class MainFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: MainFactory? = null
        fun getInstance(): MainFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MainFactory(
                    Injection.repository()
                )
            }.also { INSTANCE = it }
    }
}