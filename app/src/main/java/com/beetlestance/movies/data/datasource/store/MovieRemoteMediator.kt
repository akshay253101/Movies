package com.beetlestance.movies.data.datasource.store

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.beetlestance.movies.data.models.entities.Movies
import okio.IOException


@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val fetch: suspend (page: Long) -> Boolean
) : RemoteMediator<Int, Movies>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movies>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                    lastItem.id
                }
            }
            MediatorResult.Success(endOfPaginationReached = fetch(loadKey))

        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }
}