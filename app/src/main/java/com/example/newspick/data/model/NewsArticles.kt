package com.example.newspick.data.model

data class NewsArticles(val status: String="ok" , val totalResults: Long=0L, val articles: List<Article> = emptyList())
data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description:String?=null,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class Source(val id: Any?=null, val name: String)