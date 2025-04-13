package com.example.mycleanarchitecture.data.repositories

import com.example.mycleanarchitecture.data.remote.data_source.QuotesRemoteDataSourceContract
import com.example.mycleanarchitecture.data.remote.models.response.QuotesResult
import com.example.mycleanarchitecture.domain.abstractions.QuotesRepositoryContract
import javax.inject.Inject

class QuotesRepositoryDi @Inject constructor(
    private val remoteDataSource: QuotesRemoteDataSourceContract
): QuotesRepositoryContract {
    override suspend fun fetchQuotes(testData: String): List<QuotesResult> {
        val quotesResponse = remoteDataSource.fetchQuotes(testData)
        return quotesResponse.results ?: emptyList()
    }
}