package com.beetlestance.movies.di.modules

import com.beetlestance.movies.di.scopes.ActivityScoped
import com.beetlestance.movies.ui.MainActivity
import com.beetlestance.movies.ui.MainActivityModule
import com.beetlestance.movies.ui.discover.DiscoverMoviesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            DiscoverMoviesModule::class
        ]
    )
    internal abstract fun contributesMainActivity(): MainActivity
}