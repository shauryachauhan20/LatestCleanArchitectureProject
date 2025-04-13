package com.example.mycleanarchitecture.data.repositories

import com.example.mycleanarchitecture.data.remote.data_source.QuotesRemoteDataSourceContract
import com.example.mycleanarchitecture.fakeQuotesLists
import com.example.mycleanarchitecture.fakeQuotesResponse
import com.example.mycleanarchitecture.fakeTestdata
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class QuotesRepositoryDiTest {
    @MockK
    lateinit var remoteDataSource: QuotesRemoteDataSourceContract

    private lateinit var repository: QuotesRepositoryDi

    @Before
    fun setup() {
        // Initialize MockK annotations
        MockKAnnotations.init(this)
        // Inject the mocked remote data source into the repository
        repository = QuotesRepositoryDi(remoteDataSource)

    }

    @Test
    fun fetchQuotes_passingTestData_returnsQuotesResult() =
        runBlocking { // Wrap the call in runBlocking, which provides a coroutine context for testing purposes.
            // GIVEN: A sample test data and mock response
            getQuotesList()

            // WHEN: Calling the fetchQuotes method
            val result = repository.fetchQuotes(fakeTestdata)

            assertEquals(
                fakeQuotesLists,
                result,
                "The returned quotes results do not match expected values"
            )

            // Verify that the remoteDataSource was called exactly once
            coVerify(exactly = 1) { remoteDataSource.fetchQuotes(fakeTestdata) }
        }

    private fun getQuotesList() {
        //cannot directly replace coEvery with every because coEvery is specifically designed for suspend functions
        //For suspend functions: Use coEvery and coVerify
        //For regular functions: Use every and verify
        coEvery {
            remoteDataSource.fetchQuotes(fakeTestdata)
        } returns fakeQuotesResponse
    }
}