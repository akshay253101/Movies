package com.beetlestance.movies.data.datasource

import android.content.Context
import androidx.room.Room
import com.beetlestance.movies.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "movies.db"
    ).build()

}