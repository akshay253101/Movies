package com.beetlestance.movies

import com.beetlestance.movies.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MoviesApplication : DaggerApplication() {

    /**
     *  Injects dagger modules in applications core component
     */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}