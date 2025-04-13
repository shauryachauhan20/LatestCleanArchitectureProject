package com.example.mycleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycleanarchitecture.domain.use_cases.FetchQuotesUseCase

class QuoteViewModelFactory(
    private val fetchQuotesUseCase: FetchQuotesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
            return QuoteViewModel(fetchQuotesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}