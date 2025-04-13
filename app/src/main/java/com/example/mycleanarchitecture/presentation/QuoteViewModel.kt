package com.example.mycleanarchitecture.presentation

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mycleanarchitecture.data.remote.models.response.QuotesResult
import com.example.mycleanarchitecture.domain.use_cases.FetchQuotesUseCase
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class QuoteViewModel(
    private val fetchQuotesUseCase: FetchQuotesUseCase
): CoreViewModel() {
    private val _quotes = MutableLiveData<List<QuotesResult>>()
    val quotes: LiveData<List<QuotesResult>> get() = _quotes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    @get:Bindable
    var progressBar: Boolean by Delegates.observable(false){_,_,_->
        notifyPropertyChanged(BR.progressBar)
    }

    fun fetchQuotes(testData: String){
        progressBar = true
        viewModelScope.launch {
            try {
                val response = fetchQuotesUseCase.execute(testData)
                _quotes.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                progressBar = false
            }
        }
    }
}