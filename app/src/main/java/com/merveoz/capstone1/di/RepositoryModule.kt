package com.merveoz.capstone1.di

import com.merveoz.capstone1.data.repository.ProductRepository
import com.merveoz.capstone1.data.source.local.ProductDao
import com.merveoz.capstone1.data.source.remote.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsRepository(
        productService: ProductService,
        productDao: ProductDao
    ) = ProductRepository(productService, productDao)
}