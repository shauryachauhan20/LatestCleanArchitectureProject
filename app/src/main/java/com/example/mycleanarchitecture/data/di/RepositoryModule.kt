package com.example.mycleanarchitecture.data.di

import com.example.mycleanarchitecture.data.remote.ApiService
import com.example.mycleanarchitecture.data.remote.data_source.QuotesRemoteDataSourceContract
import com.example.mycleanarchitecture.data.remote.data_source.QuotesRemoteDiDataSource
import com.example.mycleanarchitecture.data.repositories.QuotesRepositoryDi
import com.example.mycleanarchitecture.domain.abstractions.QuotesRepositoryContract
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
    fun provideQuotesRemoteDataSource(apiService: ApiService): QuotesRemoteDataSourceContract {
        return QuotesRemoteDiDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideQuotesRepository(remoteDataSource: QuotesRemoteDataSourceContract): QuotesRepositoryContract {
        return QuotesRepositoryDi(remoteDataSource)
    }
}