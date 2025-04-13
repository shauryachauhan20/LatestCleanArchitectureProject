package com.example.mycleanarchitecture.data.remote.data_source

import com.example.mycleanarchitecture.data.remote.ApiService

class QuotesRemoteDataSource(
    private val service: ApiService
): QuotesRemoteDataSourceContract {
    override suspend fun fetchQuotes(testData: String) = service.fetchQuotesList()
}