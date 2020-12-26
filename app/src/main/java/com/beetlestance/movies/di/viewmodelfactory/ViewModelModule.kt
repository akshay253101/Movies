package com.beetlestance.movies.di.viewmodelfactory

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 *
 * Connection between our ViewModelFactory and android ViewModelFactory
 */
@Module
internal abstract class ViewModelModule {

    /**
     * Generate binding method for ViewModelProvider.Factory
     *
     * Change the return type for using different ViewModelFactory
     */
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}