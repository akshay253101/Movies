package com.beetlestance.movies.ui.discover

import androidx.lifecycle.ViewModel
import com.beetlestance.movies.di.scopes.FragmentScoped
import com.beetlestance.movies.di.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DiscoverMoviesModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesDiscoverMoviesFragment(): DiscoverMoviesFragment

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverMoviesViewModel::class)
    abstract fun bindsDiscoverMoviesViewModel(viewModel: DiscoverMoviesViewModel): ViewModel
}