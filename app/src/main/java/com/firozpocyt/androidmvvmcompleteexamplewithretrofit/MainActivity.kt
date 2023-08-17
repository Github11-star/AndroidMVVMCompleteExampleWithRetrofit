package com.firozpocyt.androidmvvmcompleteexamplewithretrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.repository.Response
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.viewmodels.MainViewModel
import com.firozpocyt.androidmvvmcompleteexamplewithretrofit.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).quoteRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.quotes.observe(this, Observer {
            when(it){
                is Response.Loading ->{}
                is Response.Error -> {
                    Toast.makeText(this@MainActivity, "Some error occurred", Toast.LENGTH_SHORT).show()
                }
                is Response.Success -> {
                    it.data?.let {
                        Toast.makeText(this@MainActivity, it.results.size.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}