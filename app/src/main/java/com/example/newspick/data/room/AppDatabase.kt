package com.example.newspick.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newspick.data.room.dao.BookmarkedArticleDao
import com.example.newspick.data.room.model.BookmarkedArticle
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [BookmarkedArticle::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun bookmarkArticleDao():BookmarkedArticleDao
    companion object{
        @Volatile
        private var INSTANCE:AppDatabase?=null

        @InternalCoroutinesApi
        fun getDatabase(context: Context):AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .build()
                INSTANCE=instance
                instance
            }
        }
    }
}