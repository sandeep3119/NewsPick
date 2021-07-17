package com.example.newspick.repository

import android.util.Log
import com.example.newspick.api.ApiHelper
import com.example.newspick.data.model.Article
import com.example.newspick.data.model.NewsArticles
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getArticles() = runCatching {
        apiHelper.getArticles()
    }.getOrElse {
        Log.d("yolo",it.toString())
        NewsArticles()
    }
}