package com.example.newspick.data.model

data class NewsArticles(val status: String , val totalResults: Long, val articles: List<Article>)
data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: Any?=null,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class Source(val id: Any?=null, val name: String)