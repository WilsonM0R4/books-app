package com.example.booksapp.interfaces

import com.example.booksapp.model.Book

interface BookCallback {
    fun onSuccess(book: Book)
}