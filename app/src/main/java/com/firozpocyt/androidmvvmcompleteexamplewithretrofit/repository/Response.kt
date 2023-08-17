package com.firozpocyt.androidmvvmcompleteexamplewithretrofit.repository

import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.models.QuoteList
import java.util.logging.ErrorManager


/*
sealed class Response() {
    class Loading : Response()
    class Success(val quoteList: QuoteList) : Response()
    class Error(val errorMessage: String) : Response()
}*/

/*sealed class Response(val data: QuoteList? = null, val errorMessage: String? = null) {
    class Loading : Response()
    class Success(quoteList: QuoteList) : Response(data = quoteList)
    class Error(errorMessage: String) : Response(errorMessage = errorMessage)
}*/

sealed class Response<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(errorMessage: String) : Response<T>(errorMessage = errorMessage)
}
