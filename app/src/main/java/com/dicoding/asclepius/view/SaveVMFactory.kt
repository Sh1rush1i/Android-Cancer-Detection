package com.dicoding.asclepius.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SaveVMFactory(private val application: Application): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SaveVM::class.java)) {
            return SaveVM(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: SaveVMFactory? = null

        fun getInstance(application: Application): SaveVMFactory =
            instance ?: synchronized(this) {
                instance ?: SaveVMFactory(application)
            }.also { instance = it }
    }
}