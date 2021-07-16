package com.example.newspick.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newspick.data.room.model.BookmarkedArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkedArticleDao {
    @Query("Select * from article order by id desc")
    suspend fun getAll(): List<BookmarkedArticle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: BookmarkedArticle)
}