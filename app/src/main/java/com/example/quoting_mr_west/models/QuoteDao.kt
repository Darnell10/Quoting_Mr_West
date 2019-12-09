package com.example.quoting_mr_west.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import retrofit2.http.DELETE

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertAll(vararg quote: Quote_Model): List<Long>

    @Query("SELECT * FROM quote_model")
    suspend fun getallQuotes(): List<Quote_Model>

    @Query("SELECT * FROM quote_model WHERE uuid = :quoteId ")
    suspend fun getQuote(quoteId: Int): Quote_Model

    @Query("DELETE FROM quote_model")
    suspend fun deleteAllQuotes()
}