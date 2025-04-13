package com.example.mycleanarchitecture.data.remote.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuotesResult(
    val id: String?,
    val author: String?,
    val content: String?,
    val authorSlug: String?,
    val dateAdded: String?,
    val dateModified: String?,
    val length: Int?,
    val tags: List<String>?
): Parcelable