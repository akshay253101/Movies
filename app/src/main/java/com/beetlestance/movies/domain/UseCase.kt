package com.beetlestance.movies.domain

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

abstract class ResultUseCase<in P, out R> {
    operator fun invoke(params: P): Flow<R> {
        return flow { emit(doWork(params)) }
    }

    protected abstract suspend fun doWork(params: P): R
}

abstract class ObserveUseCase<P : Any, T> {
    private val paramState = MutableSharedFlow<P>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    operator fun invoke(params: P) {
        paramState.tryEmit(params)
    }

    protected abstract fun createObservable(params: P): Flow<T>

    fun observe(): Flow<T> = paramState.flatMapLatest { createObservable(it) }
}

abstract class PagingUseCase<P : PagingUseCase.Parameters<T>, T : Any> :
    ObserveUseCase<P, PagingData<T>>() {

    interface Parameters<T : Any> {
        val pagingConfig: PagingConfig
    }
}


operator fun <T> ResultUseCase<Unit, T>.invoke(): Flow<T> = invoke(Unit)
operator fun <T> ObserveUseCase<Unit, T>.invoke(): Unit = invoke(Unit)
