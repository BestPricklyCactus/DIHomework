package com.otus.dihomework.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.otus.dihomework.common.data.FavoritesRepositoryImpl
import com.otus.dihomework.common.data.ProductApiService
import com.otus.dihomework.common.data.ProductRepositoryImpl
import com.otus.dihomework.common.domain_api.ConsumeFavoritesUseCase
import com.otus.dihomework.common.domain_api.ConsumeProductsUseCase
import com.otus.dihomework.common.domain_api.ToggleFavoriteUseCase
import com.otus.dihomework.common.domain_impl.ConsumeFavoritesUseCaseImpl
import com.otus.dihomework.common.domain_impl.ConsumeProductsUseCaseImpl
import com.otus.dihomework.common.domain_impl.FavoritesRepository
import com.otus.dihomework.common.domain_impl.ProductRepository
import com.otus.dihomework.common.domain_impl.ToggleFavoriteUseCaseImpl
import com.otus.dihomework.common.util.PriceFormatter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [SubcomponentsModule::class])
object AppModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://otus-android.github.io/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApiService(retrofit: Retrofit): ProductApiService {
        return retrofit.create(ProductApiService::class.java)
    }

    @Provides
    fun provideProductRepository(): ProductRepository {
        return ProductRepositoryImpl()
    }

    @Provides
    fun provideFavoritesRepository(context: Context): FavoritesRepository {
        return FavoritesRepositoryImpl(context)
    }

    @Provides
    fun provideConsumeProductsUseCase(): ConsumeProductsUseCase {
        return ConsumeProductsUseCaseImpl()
    }

    @Provides
    fun provideConsumeFavoritesUseCase(): ConsumeFavoritesUseCase {
        return ConsumeFavoritesUseCaseImpl()
    }

    @Provides
    fun provideToggleFavoriteUseCase(): ToggleFavoriteUseCase {
        return ToggleFavoriteUseCaseImpl()
    }

    @Provides
    fun providePriceFormatter(): PriceFormatter {
        return PriceFormatter()
    }
}
