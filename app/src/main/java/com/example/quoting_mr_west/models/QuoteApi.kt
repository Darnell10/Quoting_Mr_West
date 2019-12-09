package com.example.quoting_mr_west.models

import io.reactivex.Single
import retrofit2.http.GET

interface QuoteApi {

    @GET("quote")
    fun getQuote(): Single<List<Quote_Model>>
}