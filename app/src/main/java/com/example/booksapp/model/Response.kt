package com.example.booksapp.model

data class Response(var error:String, var total:String, var page:String, var books:ArrayList<Book>)