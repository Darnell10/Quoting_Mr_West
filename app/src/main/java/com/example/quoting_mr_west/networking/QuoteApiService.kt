package com.example.quoting_mr_west.networking

import com.example.quoting_mr_west.models.Quote_Model
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class QuoteApiService {

    private val BASE_URL = "https://api.kanye.rest/"

    private val apiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(QuoteApi::class.java)

    fun getQuote(): Single<Quote_Model>{
        return apiService.getQuote()
    }
}