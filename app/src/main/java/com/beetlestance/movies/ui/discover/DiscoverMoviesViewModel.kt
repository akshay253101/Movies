package com.beetlestance.movies.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.beetlestance.movies.data.models.entities.Movies
import com.beetlestance.movies.domain.executors.FetchMovies
import com.beetlestance.movies.domain.executors.FetchMovies.MoviesConfig
import com.beetlestance.movies.domain.observers.ObserveMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverMoviesViewModel @Inject constructor(
    private val fetchMovies: FetchMovies,
    private val observeMovies: ObserveMovies
) : ViewModel() {

    private val _pageConfig: MutableLiveData<MoviesConfig> = MutableLiveData()
    val pageConfig: LiveData<MoviesConfig> = _pageConfig

    val searchQuery: MutableLiveData<String> = MutableLiveData()

    val movies: Flow<PagingData<Movies>>
        get() = observeMovies.observe()

    init {
        viewModelScope.launch {
            fetchMovies.invoke(Unit).collect { config ->
                _pageConfig.postValue(config)
            }
        }
        observeMovies(ObserveMovies.Params(PAGING_CONFIG))
    }

    fun onImeActionTriggered() {
        observeMovies(ObserveMovies.Params(PAGING_CONFIG))
    }

    companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
        )
    }
}