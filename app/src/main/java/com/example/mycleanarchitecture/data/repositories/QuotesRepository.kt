package com.example.mycleanarchitecture.data.repositories

import com.example.mycleanarchitecture.data.remote.data_source.QuotesRemoteDataSourceContract
import com.example.mycleanarchitecture.data.remote.models.response.QuotesResult
import com.example.mycleanarchitecture.domain.abstractions.QuotesRepositoryContract

class QuotesRepository(
    private val remoteDataSource: QuotesRemoteDataSourceContract
): QuotesRepositoryContract {
    override suspend fun fetchQuotes(testData: String): List<QuotesResult> {
        // Fetch QuotesResponse from the remote data source
        val quotesResponse = remoteDataSource.fetchQuotes(testData)

   /*     // Map the data model (QuotesResponse) to domain model (QuotesResult)
        return quotesResponse.map { response ->
            QuotesResult(
                id = response.id,
                author = response.au,
                content = response.content,
                authorSlug = response.author,
                dateAdded = ,
                length = ,
                tags =response
            )
        }*/

        // Return the list of results from the response
        return quotesResponse.results ?: emptyList()
    }
}