package com.example.booksapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksapp.interfaces.BookCallback
import com.example.booksapp.interfaces.FailureCallback
import com.example.booksapp.model.ApiClient
import com.example.booksapp.model.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    var respBook : MutableLiveData<Book> = MutableLiveData<Book>()
    private val client = ApiClient()

    fun getBookDetail(context: Context, id:String) {
        CoroutineScope(Dispatchers.IO).launch {
            client.getBookDetail(id, object: BookCallback {
                override fun onSuccess(book: Book) {
                     respBook.value = book
                }
            }, object: FailureCallback {
                override fun onProcessFailed() {
                    Toast.makeText(context,
                        "Ha ocurrido un error al consultar. Intenta nuevamente.",
                        Toast.LENGTH_LONG)
                        .show()
                }
            })
        }

    }

}