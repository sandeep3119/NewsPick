package com.example.newspick.repository

import android.util.Log
import com.example.newspick.api.ApiService
import com.example.newspick.data.model.NewsArticles
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getArticles() = runCatching {
        val helper=apiService.getArticles()
        helper
    }.getOrElse {
        Log.d("yolo",it.toString())
        NewsArticles()
    }
}