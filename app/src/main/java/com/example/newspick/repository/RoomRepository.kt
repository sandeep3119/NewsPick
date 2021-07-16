package com.example.newspick.repository

import com.example.newspick.data.room.dao.BookmarkedArticleDao
import com.example.newspick.data.room.model.BookmarkedArticle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepository @Inject constructor(private val dao: BookmarkedArticleDao) {
    suspend fun getAllArticles(): List<BookmarkedArticle> =dao.getAll()
    suspend fun insertArticle(article: BookmarkedArticle)=dao.insertArticle(article)
}