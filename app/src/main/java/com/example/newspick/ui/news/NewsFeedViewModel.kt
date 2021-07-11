package com.example.newspick.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspick.data.model.Article
import com.example.newspick.data.model.NewsArticles
import com.example.newspick.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private var _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    fun fetch() {
        viewModelScope.launch {
            _articles.postValue(mainRepository.getArticles().articles)
        }
    }
}