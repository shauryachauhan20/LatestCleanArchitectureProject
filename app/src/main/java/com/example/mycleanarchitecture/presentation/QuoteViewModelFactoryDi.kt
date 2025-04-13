package com.example.mycleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycleanarchitecture.domain.use_cases.FetchQuotesDiUseCase

class QuoteViewModelFactoryDi(
    private val fetchQuotesUseCase: FetchQuotesDiUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModelDi::class.java)) {
            return QuoteViewModelDi(fetchQuotesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}