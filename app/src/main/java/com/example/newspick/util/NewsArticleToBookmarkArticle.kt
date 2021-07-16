package com.example.newspick.util

import com.example.newspick.data.model.Article
import com.example.newspick.data.model.NewsArticles
import com.example.newspick.data.room.model.BookmarkedArticle

class NewsArticleToBookmarkArticle {
    fun convertNewsToBookmark(article:Article):BookmarkedArticle{
        return BookmarkedArticle(0,article.title,article.description,article.url,article.urlToImage)
    }
}