package com.dicoding.mysubmission.di


import com.dicoding.mysubmission.data.remote.Repository
import com.dicoding.mysubmission.data.remote.retrofit.ApiConfig

object Injection {
    fun repository(): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}