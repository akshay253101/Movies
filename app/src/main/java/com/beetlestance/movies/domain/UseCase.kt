package com.beetlestance.movies.domain

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

abstract class ResultUseCase<in P, out R> {
    operator fun invoke(params: P): Flow<R> {
        return flow {
            emit(doWork(params))
        }.catch { throwable ->
            // report to crashlytics
        }
    }

    protected abstract suspend fun doWork(params: P): R
}

abstract class ObserveUseCase<P : Any, T> {
    private val paramState = MutableSharedFlow<P>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    operator fun invoke(params: P) {
        paramState.tryEmit(params)
    }

    protected abstract fun createObservable(params: P): Flow<T>

    fun observe(): Flow<T> = paramState.flatMapLatest {
        createObservable(it).catch { throwable ->
            // report to crashlytics
        }
    }
}


operator fun <T> ResultUseCase<Unit, T>.invoke(): Flow<T> = invoke(Unit)
operator fun <T> ObserveUseCase<Unit, T>.invoke(): Unit = invoke(Unit)
