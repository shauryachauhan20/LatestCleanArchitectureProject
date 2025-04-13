package com.example.mycleanarchitecture.domain.use_cases

import com.example.mycleanarchitecture.data.remote.models.response.QuotesResult
import com.example.mycleanarchitecture.domain.abstractions.QuotesRepositoryContract
import javax.inject.Inject

class FetchQuotesDiUseCase @Inject constructor(
    private val repository: QuotesRepositoryContract
) {
    suspend fun execute(testData: String): List<QuotesResult> {
        return repository.fetchQuotes(testData)
    }
}