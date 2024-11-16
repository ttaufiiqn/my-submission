package com.dicoding.mysubmission.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dicoding.mysubmission.data.remote.response.AddStoryResponse
import com.dicoding.mysubmission.data.remote.response.ListStoryItem
import com.dicoding.mysubmission.data.remote.response.LoginResponse
import com.dicoding.mysubmission.data.remote.response.RegisterResponse
import com.dicoding.mysubmission.data.remote.response.Story
import com.dicoding.mysubmission.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import com.dicoding.mysubmission.data.Result

class Repository private constructor(
    private val apiService: ApiService
){
    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.login(email, password)
                if (!response.error) {
                    Result.Success(response)
                } else {
                    Result.Error(response.message)
                }
            } catch (e: Exception) {
                Result.Error("${e.message}")
            }
        }
    }


    suspend fun register(name: String, email: String, password: String): Result<RegisterResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.register(name, email, password)

                if (!response.error) {
                    Result.Success(response)
                } else {
                    Result.Error(response.message)
                }
            } catch (e: HttpException) {
                Result.Error("${e.message}")
            }
        }
    }

    suspend fun getDetail(token: String, id: String): Result<Story> {
        return withContext(Dispatchers.IO) {
            try {
                val token = "Bearer $token"
                val response = apiService.getDetail(token, id)
                if (!response.error) {
                    Result.Success(response.story)
                } else {
                    Result.Error(response.message)
                }
            } catch (e: HttpException) {
                Result.Error("Http Exception: ${e.message}")
            } catch (e: Exception) {
                Result.Error("An error occured: ${e.message}")
            }
        }
    }

    suspend fun addStory(
        token: String,
        description: RequestBody,
        photo: MultipartBody.Part,
        lat: RequestBody? = null,
        lon: RequestBody? = null
    ): Result<AddStoryResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val token = "Bearer $token"
                val response = apiService.addStory(token, description, photo, lat, lon)
                if (!response.error) {
                    Result.Success(response)
                } else {
                    Result.Error(response.message)
                }
            } catch (e: HttpException) {
                Result.Error("HTTP Exception: ${e.message}")
            } catch (e: Exception) {
                Result.Error("An error occurred: ${e.message}")
            }
        }
    }

    fun getStoriesPaging(token: String): LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoryPagingSource(apiService, token)
            }
        ).liveData
    }
    companion object {
        @Volatile
        private var INSTANCE: Repository? = null
        fun getInstance(
            apiService: ApiService
        ): Repository = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Repository(apiService)
        }.also { INSTANCE = it }
    }
}