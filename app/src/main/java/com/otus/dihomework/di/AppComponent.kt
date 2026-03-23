package com.otus.dihomework.di

import android.content.Context
import com.otus.dihomework.common.data.ProductApiService
import com.otus.dihomework.common.domain_api.ConsumeFavoritesUseCase
import com.otus.dihomework.common.domain_api.ConsumeProductsUseCase
import com.otus.dihomework.common.domain_api.ToggleFavoriteUseCase
import com.otus.dihomework.common.domain_impl.FavoritesRepository
import com.otus.dihomework.common.domain_impl.ProductRepository
import com.otus.dihomework.common.util.PriceFormatter
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : ProductsDependencies {
    fun context(): Context
    fun okHttpClient(): OkHttpClient
    fun retrofit(): Retrofit
    fun productApiService(): ProductApiService
    fun productRepository(): ProductRepository
    fun favoritesRepository(): FavoritesRepository
    override fun consumeProductsUseCase(): ConsumeProductsUseCase
    fun consumeFavoritesUseCase(): ConsumeFavoritesUseCase
    override fun toggleFavoriteUseCase(): ToggleFavoriteUseCase
    override fun priceFormatter(): PriceFormatter
    fun favoritesComponent(): FavoritesComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
