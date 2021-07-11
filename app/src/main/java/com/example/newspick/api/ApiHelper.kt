package com.example.newspick.api

import com.example.newspick.data.model.NewsArticles
import retrofit2.Response

interface ApiHelper{
    suspend fun getArticles():NewsArticles
}