package com.example.mycleanarchitecture.data.remote.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuotesResponse(
    val count: Int?,
    val lastItemIndex: Int?,
    val page: Int?,
    val results: List<QuotesResult>?,
    val totalCount: Int?,
    val totalPages: Int?
): Parcelable