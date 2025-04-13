package com.example.mycleanarchitecture.data.remote

import com.example.mycleanarchitecture.data.remote.models.response.QuotesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("quotes")
    suspend fun fetchQuotesList(): QuotesResponse
}