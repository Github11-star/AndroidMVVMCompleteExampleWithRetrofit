package com.firozpocyt.androidmvvmcompleteexamplewithretrofit

import android.app.Application
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.api.QuoteService
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.api.RetrofitHelper
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.db.QuoteDatabase
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, applicationContext)
    }
}