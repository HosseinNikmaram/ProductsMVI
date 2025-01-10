package com.nikmaram.data.repository.di

import com.nikmaram.data.repository.ProductImp
import com.nikmaram.data.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class repositoryModule {
    @Binds
    abstract fun bindProductRepository(
        productImp: ProductImp
    ) :ProductRepository
}