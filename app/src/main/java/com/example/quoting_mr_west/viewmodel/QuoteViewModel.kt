package com.example.quoting_mr_west.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.quoting_mr_west.models.QuoteApiService
import com.example.quoting_mr_west.models.Quote_Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class QuoteViewModel(application: Application) : AndroidViewModel(application) {

    private val quoteApiService = QuoteApiService()
    private val disposable = CompositeDisposable()

    val quote = MutableLiveData<Quote_Model>()
    val quoteLoaderError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        fetchFromRemote()
    }
    fun refresh() {

        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            quoteApiService.getQuote()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Quote_Model>() {
                    override fun onSuccess(quoteModel: Quote_Model) {
                        quoteLoaderError.value = false
                        quote.value = quoteModel
                        loading.value = false
                        Log.e("ViewModel", "${quote.value}")
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        quote.value = null
                        quoteLoaderError.value = true
                    }

                })
        )

    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}