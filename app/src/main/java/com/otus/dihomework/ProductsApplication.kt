package com.otus.dihomework

import android.app.Application
import com.otus.dihomework.di.AppComponent
import com.otus.dihomework.di.DaggerAppComponent
import com.otus.dihomework.di.Dependencies
import com.otus.dihomework.di.DependenciesProvider

class ProductsApplication : Application(), DependenciesProvider {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        ServiceLocator.init(this)
    }

    override fun getDependencies(): Dependencies {
        return appComponent
    }
}
