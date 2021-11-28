package com.example.booksapp.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.booksapp.R
import com.example.booksapp.databinding.ActivityDetailBinding
import com.example.booksapp.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isbn13 = intent.getStringExtra("isbn13")

        viewModel = DetailViewModel()
        viewModel.respBook.observe(this, Observer {
            Picasso.get().load(it.image).into(binding.imageView2)
            binding.progressBar2.visibility = View.GONE
            binding.detailTitle.text = String.format(resources.getString(R.string.book_title_ph),it.title )
            binding.detailSubtitle.text = String.format(resources.getString(R.string.book_subtitle_ph), it.subtitle)
            binding.detailAuthors.text = String.format(resources.getString(R.string.book_authors_ph), it.authors)
            binding.detailDesc.text = it.desc
            binding.detailLanguage.text  = String.format(resources.getString(R.string.book_language_ph), it.language)
            binding.detailPages.text = String.format(resources.getString(R.string.book_pages_ph), it.pages)
            binding.detailPrice.text = String.format(resources.getString(R.string.book_price_ph), it.price)
            binding.detailRating.text = String.format(resources.getString(R.string.book_rating_ph), it.rating)
            binding.detailPublisher.text = String.format(resources.getString(R.string.book_publisher_ph),it.publisher)
            binding.detailYear.text = String.format(resources.getString(R.string.book_year_ph), it.year)
            binding.detailUrl.text = it.url
            binding.isbn13.text = String.format(resources.getString(R.string.book_isbn13_ph), it.isbn13)
        })

        binding.progressBar2.visibility = View.VISIBLE

        binding.detailUrl.setOnClickListener {
            if (binding.detailUrl.text != null) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.detailUrl.text.toString()))
                startActivity(intent)
            }
        }

        viewModel.getBookDetail(this, isbn13!!)
    }
}