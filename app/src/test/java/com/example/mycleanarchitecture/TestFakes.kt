package com.example.mycleanarchitecture

import com.example.mycleanarchitecture.data.remote.models.response.QuotesResponse
import com.example.mycleanarchitecture.data.remote.models.response.QuotesResult

const val fakeId = 0
const val fakeTestdata = ""

val tagList = listOf("hello", "Hi", "hey")
val fakeQuotesLists = listOf(
    QuotesResult(
        id = "1",
        author = "Author 1",
        content = "Quote 1",
        authorSlug = "as",
        dateAdded = "",
        dateModified = "",
        length = 1,
        tags = tagList
    ),
    QuotesResult(
        id = "2",
        author = "Author 2",
        content = "Quote 2",
        authorSlug = "",
        dateAdded = "",
        dateModified = "",
        length = 1,
        tags = tagList
    )
)

val fakeQuotesResponse = QuotesResponse(
    count = 1,
    lastItemIndex = 10,
    page = 1,
    results = fakeQuotesLists,
    totalCount = 5,
    totalPages = 5
)