package com.dicoding.asclepius.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.History
import com.dicoding.asclepius.data.HistoryRepository

class SaveVM(application: Application):ViewModel() {

    private val historyRepository: HistoryRepository = HistoryRepository(application)

    fun getAllHistory() : LiveData<List<History>> = historyRepository.getAllHistory()

    fun delete(history: History) {
        historyRepository.delete(history)
    }
}