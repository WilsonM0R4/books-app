package com.example.booksapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksapp.interfaces.BookCallback
import com.example.booksapp.interfaces.FailureCallback
import com.example.booksapp.interfaces.ProcessCallback
import com.example.booksapp.model.ApiClient
import com.example.booksapp.model.Book
import com.example.booksapp.model.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BooksViewModel : ViewModel() {

    var books : MutableLiveData<ArrayList<Book>> = MutableLiveData<ArrayList<Book>>()
    private val client = ApiClient()

    fun searchBooks(context: Context, query:String, page:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            client.searchBooks(query, page, object : ProcessCallback {
                override fun onProcessFinished(response: Response) {
                    books.value = response.books
                }
            }, object : FailureCallback {
                override fun onProcessFailed() {
                    books.value = ArrayList<Book>()
                    Toast.makeText(context,
                        "Ha ocurrido un error al consultar. Intenta nuevamente.",
                        Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }

}