package com.dicoding.asclepius.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultVMFactory(private val application: Application): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultVM::class.java)) {
            return ResultVM(application) as T
        }
        throw IllegalArgumentException("Viewmodel tidak diketahui: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ResultVMFactory? = null

        fun getInstance(application: Application): ResultVMFactory =
            instance ?: synchronized(this) {
                instance ?: ResultVMFactory(application)
            }.also { instance = it }
    }
}