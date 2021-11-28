package com.example.booksapp.interfaces

import com.example.booksapp.model.Book
import com.example.booksapp.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BookQuery {

    @GET("search/{query}/{page}")
    fun searchBooks(@Path("query")query:String, @Path("page")page:Int) : Call<Response>

    @GET("books/{isbn13}")
    fun getBookDetail(@Path("isbn13")query:String) : Call<Book>
}