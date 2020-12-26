package com.beetlestance.movies.di.component

import com.beetlestance.movies.MoviesApplication
import com.beetlestance.movies.di.modules.ActivityModule
import com.beetlestance.movies.di.modules.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * [AppComponent] Component running on application scope
 *  AndroidInjectionModule provides injection into dagger base classes
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ApplicationModule::class
    ]
)
interface AppComponent : AndroidInjector<MoviesApplication> {

    /**
     *  @builder inject modules on app initialization
     *  @see [MoviesApplication.applicationInjector]
     */
    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<MoviesApplication>

}