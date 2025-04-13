package com.example.mycleanarchitecture.data.remote.data_source

import com.example.mycleanarchitecture.data.remote.models.response.QuotesResponse

interface QuotesRemoteDataSourceContract {
   suspend fun fetchQuotes(testData:String) : QuotesResponse
}