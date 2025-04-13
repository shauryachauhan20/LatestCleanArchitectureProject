package com.example.mycleanarchitecture.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycleanarchitecture.data.di.ApiClient
import com.example.mycleanarchitecture.data.remote.data_source.QuotesRemoteDataSource
import com.example.mycleanarchitecture.data.remote.models.response.QuotesResult
import com.example.mycleanarchitecture.data.repositories.QuotesRepository
import com.example.mycleanarchitecture.databinding.ActivityQuoteBinding
import com.example.mycleanarchitecture.domain.abstractions.QuotesRepositoryContract
import com.example.mycleanarchitecture.domain.use_cases.FetchQuotesUseCase
import com.example.mycleanarchitecture.presentation.adapter.QuotesAdapter

class QuoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuoteBinding
    val adapterQuotes = QuotesAdapter()
    private lateinit var viewModel: QuoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //To set this code(commented) -

       /* // Bind ViewModel to layout
        binding.lifecycleOwner = this
        binding.data = viewModel*/
        // I now setting binding like below

        val repository: QuotesRepositoryContract =
            QuotesRepository(QuotesRemoteDataSource(ApiClient.apiService))
        val useCase = FetchQuotesUseCase(repository)
        val viewModelFactory = QuoteViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(QuoteViewModel::class.java)

        binding = ActivityQuoteBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@QuoteActivity  // Bind to the Activity's lifecycle
            data = viewModel                    // Set the ViewModel
        }
        setContentView(binding.root)

        // Setup RecyclerView Adapter
        setupRecyclerView()

        // Observe LiveData
        observeViewModel()

        // Trigger the API call
        viewModel.fetchQuotes("testData")
    }

    private fun setAdapter(listQuotes: List<QuotesResult>) {
//        adapterSellout.setOnItemsClickListener(this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewQuotes.layoutManager = layoutManager
        binding.recyclerviewQuotes.adapter = adapterQuotes
        adapterQuotes.setDataList(listQuotes)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewQuotes.layoutManager = layoutManager
        binding.recyclerviewQuotes.adapter = adapterQuotes
    }

    private fun observeViewModel() {
        viewModel.quotes.observe(this, Observer { quotes ->
            Log.d("MainActivity", "Quotes fetched: $quotes")
            adapterQuotes.setDataList(quotes)
        })

        viewModel.error.observe(this, Observer { error ->
            Log.e("MainActivity", "Error fetching quotes: $error")
        })
    }
}