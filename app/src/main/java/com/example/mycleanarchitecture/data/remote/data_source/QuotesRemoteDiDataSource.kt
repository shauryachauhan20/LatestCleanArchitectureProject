package com.example.mycleanarchitecture.data.remote.data_source

import com.example.mycleanarchitecture.data.remote.ApiService
import javax.inject.Inject

class QuotesRemoteDiDataSource @Inject constructor(
    private val service: ApiService
) : QuotesRemoteDataSourceContract {

    override suspend fun fetchQuotes(testData: String) = service.fetchQuotesList()
}