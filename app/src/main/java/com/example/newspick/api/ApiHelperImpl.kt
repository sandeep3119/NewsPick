package com.example.newspick.api

import com.example.newspick.data.model.NewsArticles
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(val apiService: ApiService):ApiHelper {
    override suspend fun getArticles(): NewsArticles = apiService.getArticles()
}