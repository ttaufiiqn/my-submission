package com.dicoding.mysubmission.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mysubmission.data.remote.Repository
import com.dicoding.mysubmission.di.Injection
import com.dicoding.mysubmission.ui.viewmodel.StoryMapsViewModel

class StoryMapsFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoryMapsViewModel::class.java)) {
            return StoryMapsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: StoryMapsFactory? = null
        fun getInstance(): StoryMapsFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoryMapsFactory(
                    Injection.repository()
                )
            }.also { INSTANCE = it }
    }


}