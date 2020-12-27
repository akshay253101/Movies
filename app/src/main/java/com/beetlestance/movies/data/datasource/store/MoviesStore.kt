package com.beetlestance.movies.data.datasource.store

import com.beetlestance.movies.data.datasource.daos.MoviesDao
import com.beetlestance.movies.data.models.entities.Movies
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesStore @Inject constructor(
    private val moviesDao: MoviesDao
) {

    suspend fun isPreloadRequired() = moviesDao.allMovies().isEmpty()

    suspend fun saveMovies(movies: List<Movies>) = moviesDao.insertAll(movies)

    fun moviesPageSource() = moviesDao.pagingSource()

}