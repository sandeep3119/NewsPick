package com.example.newspick.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newspick.data.room.model.BookmarkedArticle
import com.example.newspick.databinding.BookmarkedItemBinding

class BookmarkAdapter(
    private val data: List<BookmarkedArticle>
): RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>()
{
    class BookmarkViewHolder(private var binding: BookmarkedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookmarkedArticle) {
            binding.apply {
                bookmarkTitle.text = item.title
                bookmarkDescription.text = item.description
                val imageUri = Uri.parse(item.imageUri)
                Glide.with(this.root).load(imageUri.path).centerCrop()
                    .into(bookmarkImage)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkViewHolder {
        val binding =
            BookmarkedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.BookmarkViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}