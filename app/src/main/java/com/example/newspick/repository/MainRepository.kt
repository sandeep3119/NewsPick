package com.example.newspick.repository

import com.example.newspick.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getArticles()=apiHelper.getArticles()
}