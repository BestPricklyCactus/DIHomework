package com.otus.dihomework.di

import com.otus.dihomework.features.favorites.FavoritesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object FavoritesFeatureModule {
    @Provides
    fun provideFavoritesViewModelFactory(): FavoritesViewModelFactory {
        return FavoritesViewModelFactory()
    }
}
