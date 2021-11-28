package com.example.booksapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import com.example.booksapp.databinding.BookItemBinding
import com.example.booksapp.interfaces.ItemListener
import com.example.booksapp.model.Book
import com.squareup.picasso.Picasso

class BookAdapter : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    var data : ArrayList<Book> = ArrayList()
    lateinit var listener : ItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.book_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Picasso.get().load(item.image).into(holder.binding.imageView)
        holder.binding.title.text = item.title
        holder.binding.subtitle.text = item.subtitle
        holder.binding.price.text = item.price
        holder.binding.itemContainer.setOnClickListener {
            listener?.onItemClicked(item.isbn13)
        }

    }

    override fun getItemCount(): Int {
        return data.count()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = BookItemBinding.bind(itemView)
    }
}