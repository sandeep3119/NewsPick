package com.example.newspick.api

import com.example.newspick.data.model.NewsArticles
import retrofit2.Response
import retrofit2.http.GET
interface ApiService {
    @GET("NewsAPI/top-headlines/category/health/in.json")
    suspend fun getArticles(): NewsArticles
}