package com.beetlestance.movies.data.datasource

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseDaoModule {

    @Provides
    @Singleton
    fun provideMoviesDao(db: AppDatabase) = db.moviesDao()

}