package com.example.booksapp.model

import com.example.booksapp.interfaces.BookCallback
import com.example.booksapp.interfaces.BookQuery
import com.example.booksapp.interfaces.FailureCallback
import com.example.booksapp.interfaces.ProcessCallback
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private fun getClient() : Retrofit{
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Content-Type", "application/json")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()
        return Retrofit.Builder().baseUrl("https://api.itbook.store/1.0/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    fun searchBooks(query:String, page:Int, successCallback:ProcessCallback, failureCallback:FailureCallback){
        getClient().create(BookQuery::class.java).searchBooks(query, page)
            .enqueue(object:Callback<com.example.booksapp.model.Response> {
                override fun onResponse(
                    call: Call<com.example.booksapp.model.Response>,
                    response: Response<com.example.booksapp.model.Response>
                ) {
                    response.body()?.let { successCallback.onProcessFinished(it) }
                }

                override fun onFailure(
                    call: Call<com.example.booksapp.model.Response>,
                    t: Throwable
                ) {
                    failureCallback.onProcessFailed()
                }

            })
    }

    fun getBookDetail(id:String,successCallback:BookCallback, failureCallback:FailureCallback) {
        getClient().create(BookQuery::class.java).getBookDetail(id)
            .enqueue(object : Callback<Book> {
                override fun onResponse(call: Call<Book>, response: Response<Book>) {
                    response.body()?.let { successCallback.onSuccess(it) }
                }

                override fun onFailure(call: Call<Book>, t: Throwable) {
                    failureCallback.onProcessFailed()
                }

            })
    }

}