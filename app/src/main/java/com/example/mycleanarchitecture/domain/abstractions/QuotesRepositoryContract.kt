package com.example.mycleanarchitecture.domain.abstractions

import com.example.mycleanarchitecture.data.remote.models.response.QuotesResult

interface QuotesRepositoryContract {
    suspend fun fetchQuotes(testData:String) : List<QuotesResult>
}