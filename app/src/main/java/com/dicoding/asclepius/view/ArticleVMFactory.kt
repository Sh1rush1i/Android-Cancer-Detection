package com.dicoding.asclepius.view


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.data.ArticleRepository
import com.dicoding.asclepius.depinj.Injection


class ArticleVMFactory private constructor(private val repository: ArticleRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleVM::class.java)) {
            return ArticleVM(repository) as T
        }
        throw IllegalArgumentException("Kelas viewmodel tidak diketahui: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ArticleVMFactory? = null
        fun getInstance(): ArticleVMFactory =
            instance ?: synchronized(this) {
                instance ?: ArticleVMFactory(Injection.provideRepository())
            }.also { instance = it }
    }
}