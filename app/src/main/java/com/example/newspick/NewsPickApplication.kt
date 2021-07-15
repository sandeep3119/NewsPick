package com.example.newspick

import android.app.Application
import android.content.Context
import androidx.work.WorkManager
import com.example.newspick.data.room.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsPickApplication : Application() {

    private val workManager by lazy{
        WorkManager.getInstance(applicationContext)
    }
}