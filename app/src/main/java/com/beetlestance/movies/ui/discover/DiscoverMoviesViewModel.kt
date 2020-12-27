package com.beetlestance.movies.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beetlestance.movies.domain.executors.FetchMovies
import com.beetlestance.movies.domain.executors.FetchMovies.MoviesConfig
import com.beetlestance.movies.domain.invoke
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverMoviesViewModel @Inject constructor(
    private val fetchMovies: FetchMovies
) : ViewModel() {

    private val _pageConfig: MutableLiveData<MoviesConfig> = MutableLiveData()
    val pageConfig: LiveData<MoviesConfig> = _pageConfig

    init {
        viewModelScope.launch {
            fetchMovies.invoke().collect { config ->
                _pageConfig.postValue(config)
            }
        }
    }
}