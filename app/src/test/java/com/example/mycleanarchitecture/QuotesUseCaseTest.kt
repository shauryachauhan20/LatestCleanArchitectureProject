package com.example.mycleanarchitecture

import com.example.mycleanarchitecture.data.repositories.QuotesRepositoryDi
import com.example.mycleanarchitecture.domain.abstractions.QuotesRepositoryContract
import com.example.mycleanarchitecture.domain.use_cases.FetchQuotesDiUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import rx.Single

class QuotesUseCaseTest {
    @MockK
    lateinit var repository: QuotesRepositoryContract
    private lateinit var fetchQuotesDiUseCase: FetchQuotesDiUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        fetchQuotesDiUseCase = FetchQuotesDiUseCase(repository)
    }

    @Test
    fun fetchQuotes_passingTestData_returnsQuotesResult() = runBlocking{
        //Given
        coEvery {
            repository.fetchQuotes(fakeTestdata)
        } returns fakeQuotesLists

        // When
        val result = fetchQuotesDiUseCase.execute(fakeTestdata)

        // Then
        assert(result == fakeQuotesLists)
    }
}