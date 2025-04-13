package com.example.mycleanarchitecture.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycleanarchitecture.R
import com.example.mycleanarchitecture.databinding.ActivityQuoteDiBinding
import com.example.mycleanarchitecture.presentation.adapter.QuotesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuoteActivityDI : AppCompatActivity() {
    private lateinit var binding: ActivityQuoteDiBinding
    private val adapterQuotes = QuotesAdapter()

    // Injecting ViewModel using Hilt
    private val viewModel: QuoteViewModelDi by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_di)
        binding = ActivityQuoteDiBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@QuoteActivityDI
            data = viewModel
        }
        setContentView(binding.root)

        setupRecyclerView()

        observeViewModel()

        // Trigger API call
        viewModel.fetchQuotes("testData")
    }

    private fun setupRecyclerView() {
        binding.recyclerviewQuotesDi.apply {
            layoutManager = LinearLayoutManager(this@QuoteActivityDI)
            adapter = adapterQuotes
        }
    }
    private fun observeViewModel() {
        viewModel.quotes.observe(this) { quotes ->
            adapterQuotes.setDataList(quotes) // Update adapter with fetched quotes
        }

        viewModel.error.observe(this) { error ->
            Log.e("QuoteActivityDI", "Error: $error") // Log any errors
        }
    }
}