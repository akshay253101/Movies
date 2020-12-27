package com.beetlestance.movies.data.repository

import android.content.Context
import com.beetlestance.movies.constants.moviesAssests
import com.beetlestance.movies.data.datasource.store.MoviesStore
import com.beetlestance.movies.data.models.entities.Movies
import com.beetlestance.movies.data.models.response.MoviesResponse
import com.beetlestance.movies.data.models.response.Page
import com.beetlestance.movies.di.ApplicationContext
import com.beetlestance.movies.utils.loadJSONFromAsset
import com.beetlestance.movies.utils.toDataClass
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val moviesStore: MoviesStore
) {

    suspend fun fetchMovies(): Page {
        val movies = mutableListOf<Movies>()

        val isPreloadRequired = moviesStore.isPreloadRequired()

        val moviesJsonMap = moviesAssests().mapNotNull { fileName ->
            context.loadJSONFromAsset(fileName)?.toDataClass<MoviesResponse>()?.page?.also {
                if (isPreloadRequired) movies.addAll(it.contentItems.toMovies())
            }
        }

        if (moviesJsonMap.isEmpty()) throw IllegalStateException("No data found")

        if (movies.isNotEmpty()) moviesStore.saveMovies(movies.toList())

        return moviesJsonMap.first()
    }

    fun moviesPageSource(query: String) = moviesStore.moviesPageSource(query)

}