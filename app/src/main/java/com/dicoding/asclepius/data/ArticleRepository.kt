package com.dicoding.asclepius.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.asclepius.BuildConfig


class ArticleRepository(
    private val apiService: ApiService
) {

    fun getListUser(): LiveData<Result<List<ArticlesItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getArticle("cancer", "health", "en", BuildConfig.API)
            if (response.status == "ok") {
                emit(Result.Success(response.articles?.filterNotNull() ?: emptyList()))
            } else {
                emit(Result.Error("Gagal mengambil artikel"))
            }

        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}