package com.example.newspick.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newspick.R
import com.example.newspick.data.room.model.BookmarkedArticle
import com.example.newspick.databinding.BookmarkedItemBinding
import com.example.newspick.ui.bookmarked.BookmarkViewModel

class BookmarkAdapter(
    private val viewModel: BookmarkViewModel,private val clickListener:CallBackClickListener
) : ListAdapter<BookmarkedArticle, BookmarkAdapter.BookmarkViewHolder>(DiffUtilCallback) {
    object DiffUtilCallback : DiffUtil.ItemCallback<BookmarkedArticle>() {
        override fun areItemsTheSame(
            oldItem: BookmarkedArticle,
            newItem: BookmarkedArticle
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BookmarkedArticle,
            newItem: BookmarkedArticle
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkViewHolder {
        val binding =
            BookmarkedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val article=getItem(position)
        article?.let {
            holder.itemView.setOnClickListener {
                clickListener.onClick(article)
            }
        }
        holder.bind(article)
    }

    class BookmarkViewHolder(
        private var binding: BookmarkedItemBinding,
        private val viewModel: BookmarkViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookmarkedArticle) {
            binding.apply {
                bookmarkTitle.text = item.title
                bookmarkDescription.text = item.description
                val imageUri = Uri.parse(item.imageUri)
                Glide.with(this.root)
                    .load(imageUri.path)
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .into(bookmarkImage)
                deleteBookmark.setOnClickListener {
                    viewModel.deleteBookmark(item)

                }
            }
        }
}

    class CallBackClickListener(val clickListener:(article:BookmarkedArticle)->Unit){
        fun onClick(article: BookmarkedArticle)= clickListener(article)
    }


}


