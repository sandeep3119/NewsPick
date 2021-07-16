package com.example.newspick.data.room.model

import android.net.Uri
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "article",indices =[Index(value = ["title"],unique = true)])
data class BookmarkedArticle(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val title: String?,
    @ColumnInfo val description:String?,
    @ColumnInfo val url:String?,
    @ColumnInfo var imageUri:String?,
)