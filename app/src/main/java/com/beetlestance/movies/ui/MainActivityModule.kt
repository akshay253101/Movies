package com.beetlestance.movies.ui

import androidx.lifecycle.ViewModel
import com.beetlestance.movies.di.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindsMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}