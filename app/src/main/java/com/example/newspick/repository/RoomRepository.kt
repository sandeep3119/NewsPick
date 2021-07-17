package com.example.newspick.repository

import com.example.newspick.data.room.dao.BookmarkedArticleDao
import com.example.newspick.data.room.model.BookmarkedArticle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepository @Inject constructor(private val dao: BookmarkedArticleDao) {
    suspend fun getAllArticles(): List<BookmarkedArticle> = runCatching {
        dao.getAll()
    }.getOrElse {
        emptyList<BookmarkedArticle>()
    }
    suspend fun insertArticle(article: BookmarkedArticle)=dao.insertArticle(article)
    suspend fun deleteArticle(article: BookmarkedArticle)=dao.deleteArticle(article)
}