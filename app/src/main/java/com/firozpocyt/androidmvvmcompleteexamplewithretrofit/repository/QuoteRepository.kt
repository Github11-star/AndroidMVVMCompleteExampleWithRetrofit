package com.firozpocyt.androidmvvmcompleteexamplewithretrofit.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.api.QuoteService
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.db.QuoteDatabase
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.models.QuoteList
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.utils.NetworkUtils

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<Response<QuoteList>>()

    val quotes : LiveData<Response<QuoteList>>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int){

        if (NetworkUtils.isOnline(applicationContext)){
            try {
                val result = quoteService.getQuotes(page)
                if (result.body() != null){
                    quoteDatabase.quoteDao().addQuote(result.body()!!.results)
                    quotesLiveData.postValue(Response.Success(result.body()))
                }else{
                    quotesLiveData.postValue(Response.Error("Api error"))
                }
            }catch (e: Exception){
                    quotesLiveData.postValue(Response.Error(e.message.toString()))
            }

        }else {
            val quote = quoteDatabase.quoteDao().getQuote()
            val quoteList = QuoteList(1,1,1,quote,1,1)
            quotesLiveData.postValue(Response.Success(quoteList))
        }
    }

    suspend fun getQuotesBackground() {
        val randomNumber = (Math.random() * 10).toInt()
        val result = quoteService.getQuotes(randomNumber)
        if (result.body() !=null){
            quoteDatabase.quoteDao().addQuote(result.body()!!.results)
        }
    }
}