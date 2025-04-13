package com.example.mycleanarchitecture.domain.use_cases

import com.example.mycleanarchitecture.data.remote.models.response.QuotesResult
import com.example.mycleanarchitecture.domain.abstractions.QuotesRepositoryContract

class FetchQuotesUseCase(
    private val repository: QuotesRepositoryContract
) {
    suspend fun execute(testData: String): List<QuotesResult> {
        return repository.fetchQuotes(testData)
    }
}