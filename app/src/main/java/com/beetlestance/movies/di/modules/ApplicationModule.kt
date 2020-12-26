package com.beetlestance.movies.di.modules

import android.content.Context
import com.beetlestance.movies.MoviesApplication
import com.beetlestance.movies.di.AppCoroutineDispatchers
import com.beetlestance.movies.di.ApplicationContext
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * [ApplicationModule] provides app level static objects
 */
@Module
class ApplicationModule {

    /**
     *  @provides application context
     *
     *  @use @ApplicationContext val context : Context
     *
     */
    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplicationContext(moviesApplication: MoviesApplication): Context =
        moviesApplication.applicationContext

    @Singleton
    @Provides
    fun provideCoroutineDispatchers(): AppCoroutineDispatchers = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )
}