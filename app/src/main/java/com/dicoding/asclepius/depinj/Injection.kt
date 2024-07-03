package com.dicoding.asclepius.depinj

import com.dicoding.asclepius.data.ApiConfig
import com.dicoding.asclepius.data.ArticleRepository

object Injection {
    fun provideRepository(): ArticleRepository {
        val apiService = ApiConfig.getApiService()

        return ArticleRepository(apiService)
    }
}