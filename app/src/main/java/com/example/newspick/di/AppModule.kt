package com.example.newspick.di


import android.content.Context
import androidx.room.Room
import com.example.newspick.BuildConfig
import com.example.newspick.api.ApiService
import com.example.newspick.data.room.AppDatabase
import com.example.newspick.data.room.dao.BookmarkedArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   @Provides
   fun provideBaseUrl() = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context):AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"NewsPickDb").build()
    }
    @Provides
    @Singleton
    fun provideBookmarkDao(appDatabase:AppDatabase):BookmarkedArticleDao= appDatabase.bookmarkArticleDao()
}