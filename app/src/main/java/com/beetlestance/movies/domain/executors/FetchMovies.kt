package com.beetlestance.movies.domain.executors

import com.beetlestance.movies.data.repository.MoviesRepository
import com.beetlestance.movies.di.AppCoroutineDispatchers
import com.beetlestance.movies.domain.ResultUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchMovies @Inject constructor(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val moviesRepository: MoviesRepository
) : ResultUseCase<Unit, FetchMovies.MoviesConfig>() {

    override suspend fun doWork(params: Unit): MoviesConfig {
        return withContext(appCoroutineDispatchers.io) {
            val page = moviesRepository.fetchMovies()
            MoviesConfig(title = page.title, pageSize = page.pageSize)
        }
    }

    data class MoviesConfig(
        val title: String,
        val pageSize: Int
    )
}