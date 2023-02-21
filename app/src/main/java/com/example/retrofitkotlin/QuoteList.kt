package com.example.retrofitkotlin

data class QuoteList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<ResultQuote>,
    val totalCount: Int,
    val totalPages: Int
)

