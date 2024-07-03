package com.dicoding.asclepius.view

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.History
import com.dicoding.asclepius.data.HistoryRepository

class ResultVM (application: Application): ViewModel() {

    private val historyRepository: HistoryRepository = HistoryRepository(application)

    fun insert(history: History) {
        historyRepository.insert(history)
    }
}