package com.firozpocyt.androidmvvmcompleteexamplewithretrofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.models.QuoteList
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.repository.QuoteRepository
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.getQuotes(1)
        }
    }

    val quotes : LiveData<Response<QuoteList>>
        get() = quoteRepository.quotes
}