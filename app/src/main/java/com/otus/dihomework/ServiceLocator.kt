package com.otus.dihomework

import android.content.Context
import com.otus.dihomework.common.data.ProductApiService
import com.otus.dihomework.common.data.ProductDomainMapper
import com.otus.dihomework.common.data.ProductRemoteDataSource
import com.otus.dihomework.common.domain_api.ConsumeFavoritesUseCase
import com.otus.dihomework.common.domain_api.ConsumeProductsUseCase
import com.otus.dihomework.common.domain_api.ToggleFavoriteUseCase
import com.otus.dihomework.common.domain_impl.FavoritesRepository
import com.otus.dihomework.common.domain_impl.ProductRepository
import com.otus.dihomework.common.util.PriceFormatter
import com.otus.dihomework.features.favorites.FavoritesStateFactory
import com.otus.dihomework.features.products.ProductsStateFactory

object ServiceLocator {

    private var applicationContext: Context? = null

    fun init(context: Context) {
        applicationContext = context.applicationContext
    }

    private val appComponent get() =
        (checkNotNull(applicationContext) { "ServiceLocator not initialized! Call init() first." }
            as ProductsApplication).appComponent

    fun getProductApiService(): ProductApiService {
        return appComponent.productApiService()
    }

    fun getProductDomainMapper(): ProductDomainMapper {
        return ProductDomainMapper()
    }

    fun getProductRemoteDataSource(): ProductRemoteDataSource {
        return ProductRemoteDataSource()
    }

    fun getFavoritesRepository(): FavoritesRepository {
        return appComponent.favoritesRepository()
    }

    fun getProductRepository(): ProductRepository {
        return appComponent.productRepository()
    }

    fun getConsumeProductsUseCase(): ConsumeProductsUseCase {
        return appComponent.consumeProductsUseCase()
    }

    fun getConsumeFavoritesUseCase(): ConsumeFavoritesUseCase {
        return appComponent.consumeFavoritesUseCase()
    }

    fun getToggleFavoriteUseCase(): ToggleFavoriteUseCase {
        return appComponent.toggleFavoriteUseCase()
    }

    fun getPriceFormatter(): PriceFormatter {
        return appComponent.priceFormatter()
    }

    fun getProductsStateFactory(): ProductsStateFactory {
        return ProductsStateFactory()
    }

    fun getFavoritesStateFactory(): FavoritesStateFactory {
        return FavoritesStateFactory()
    }
}
