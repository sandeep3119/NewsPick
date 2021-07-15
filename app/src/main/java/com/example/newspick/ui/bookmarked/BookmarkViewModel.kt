package com.example.newspick.ui.bookmarked

import androidx.lifecycle.*
import com.example.newspick.data.room.model.BookmarkedArticle
import com.example.newspick.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(val repository: RoomRepository) : ViewModel() {

    private var _bookmarkArticles = MutableLiveData<List<BookmarkedArticle>>()
    val bookmarkArticles:LiveData<List<BookmarkedArticle>>
        get() = _bookmarkArticles

    fun fetchBookmarks() {
        viewModelScope.launch {
            _bookmarkArticles.postValue(repository.getAllArticles())
        }
    }
}