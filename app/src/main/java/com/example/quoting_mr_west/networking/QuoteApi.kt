package com.example.quoting_mr_west.networking

import com.example.quoting_mr_west.models.Quote_Model
import io.reactivex.Single
import retrofit2.http.GET

interface QuoteApi {

    @GET("  ")
    fun getQuote(): Single<Quote_Model>
}