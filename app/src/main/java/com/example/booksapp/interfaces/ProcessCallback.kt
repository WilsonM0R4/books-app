package com.example.booksapp.interfaces

import com.example.booksapp.model.Response

interface ProcessCallback {
    fun onProcessFinished(response: Response)
}