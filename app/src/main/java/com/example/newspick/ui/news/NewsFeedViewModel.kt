package com.example.newspick.ui.news

import android.app.Application
import android.net.Uri
import android.view.View
import androidx.core.net.toUri
import androidx.lifecycle.*
import androidx.work.*
import com.example.newspick.NewsPickApplication
import com.example.newspick.data.model.Article
import com.example.newspick.data.room.model.BookmarkedArticle
import com.example.newspick.repository.MainRepository
import com.example.newspick.repository.RoomRepository
import com.example.newspick.util.NewsArticleToBookmarkArticle
import com.example.newspick.worker.ImageDownloadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val roomRepository: RoomRepository, application: Application,
) :
    AndroidViewModel(application) {

    private val _isValueInserted = MutableLiveData<Boolean>()
    val isValueInserted: LiveData<Boolean>
        get() = _isValueInserted
    private val workManager = WorkManager.getInstance(application)
    private var articleToSave = BookmarkedArticle(0, null, null, null, null)
    internal val outputWorkInfo: LiveData<List<WorkInfo>>
        get() = workManager.getWorkInfosByTagLiveData("imageWork")

    private var _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    fun fetch() {
        viewModelScope.launch {
            _articles.postValue(mainRepository.getArticles().articles)
        }
    }

    fun scheduleImageWork(article: Article) {
        articleToSave = NewsArticleToBookmarkArticle().convertNewsToBookmark(article)
        createOneTimeRequest(article.urlToImage)
    }

    fun insertBookmark(imageUriFinal: String) {
        if (articleToSave.title != null) {
            viewModelScope.launch {
                articleToSave.imageUri = imageUriFinal
                roomRepository.insertArticle(articleToSave)

            }
        }
    }

    private fun createOneTimeRequest(imageUrl: String) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .build()
        val data = Data.Builder().putString(
            "imageUrl",
            imageUrl
        ).build()
        val imageWorker = OneTimeWorkRequestBuilder<ImageDownloadWorker>()
            .setConstraints(constraints)
            .setInputData(data)
            .addTag("imageWork")
            .build()
        workManager.beginWith(imageWorker).enqueue()
    }
}