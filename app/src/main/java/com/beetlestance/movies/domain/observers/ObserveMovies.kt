package com.beetlestance.movies.domain.observers

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.beetlestance.movies.data.datasource.store.MovieRemoteMediator
import com.beetlestance.movies.data.models.entities.Movies
import com.beetlestance.movies.data.repository.MoviesRepository
import com.beetlestance.movies.domain.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMovies @Inject constructor(
    private val moviesRepository: MoviesRepository
) : PagingUseCase<ObserveMovies.Params, Movies>() {

    @OptIn(ExperimentalPagingApi::class)
    override fun createObservable(params: Params): Flow<PagingData<Movies>> {
        return Pager(
            config = params.pagingConfig,
            remoteMediator = MovieRemoteMediator { itemId ->
                (itemId == INIT_DATA).also { shouldFetch ->
                    if (shouldFetch) moviesRepository.fetchMovies()
                }
            },
            pagingSourceFactory = moviesRepository::moviesPageSource
        ).flow
    }

    data class Params(
        override val pagingConfig: PagingConfig,
    ) : Parameters<Movies>

    companion object {
        private const val INIT_DATA = 0L
    }
}