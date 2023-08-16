package com.firozpocyt.androidmvvmcompleteexamplewithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.api.QuoteService
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.api.RetrofitHelper
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.repository.QuoteRepository
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.viewmodels.MainViewModel
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)

        val repository = QuoteRepository(quoteService)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.quotes.observe(this, Observer {
            Log.d("FirozPOC", it.results.toString())
        })
    }
}