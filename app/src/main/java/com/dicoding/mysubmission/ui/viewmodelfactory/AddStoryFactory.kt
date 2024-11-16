package com.dicoding.mysubmission.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mysubmission.data.remote.Repository
import com.dicoding.mysubmission.di.Injection
import com.dicoding.mysubmission.ui.viewmodel.AddStoryViewModel

class AddStoryFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddStoryViewModel::class.java)) {
            return AddStoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: AddStoryFactory? = null
        fun getInstance(): AddStoryFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: AddStoryFactory(
                    Injection.repository()
                )
            }.also { INSTANCE = it }
    }
}