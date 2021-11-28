package com.example.booksapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksapp.adapters.BookAdapter
import com.example.booksapp.databinding.ActivityMainBinding
import com.example.booksapp.interfaces.ItemListener
import com.example.booksapp.viewmodel.BooksViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = BooksViewModel()

        val adapter = BookAdapter()
        val intent = Intent(this, DetailActivity::class.java)
        adapter.listener = object : ItemListener {
            override fun onItemClicked(isbn13: String) {
                intent.putExtra("isbn13", isbn13)
                startActivity(intent)
            }
        }
        binding.rvBooks.adapter = adapter
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        viewModel.books.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            adapter.data = it
            adapter.notifyDataSetChanged()

        })

        binding.button.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            adapter.data = ArrayList()
            adapter.notifyDataSetChanged()
            viewModel.searchBooks(this, binding.editTextTextPersonName.text.toString(), 1)
        }

        binding.editTextTextPersonName.setOnEditorActionListener { _, i, _ ->
            return@setOnEditorActionListener when (i) {
                EditorInfo.IME_ACTION_SEND -> {
                    true
                }
                else -> false
            }

        }
    }
}