package com.example.newspick.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newspick.MainActivity
import com.example.newspick.R
import com.example.newspick.data.model.Article
import com.example.newspick.databinding.NewsFeedItemBinding
import com.example.newspick.ui.news.NewsFeedViewModel

class NewsAdapter( private val viewModel: NewsFeedViewModel) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var data: List<Article> = emptyList()

    fun updateData(updatedData:List<Article>){
        data=updatedData
    }
    class NewsViewHolder(
        val binding: NewsFeedItemBinding,
        private val viewModel: NewsFeedViewModel
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Article) {
            with(binding) {
                newsTitle.text = item.title
                newsAuthor.text = item.author
                newsDescription.text = item.description.toString()
                newsPublishedAt.text = item.publishedAt
                Glide.with(binding.root)
                    .load(item.urlToImage)
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .into(newsImage)

                bookmarkButton.setOnClickListener {
                    viewModel.setWorkProgress(true)
                    viewModel.scheduleImageWork(item)
                    bookmarkButton.visibility= View.GONE
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: NewsFeedItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_feed_item, parent, false
        )
        return NewsViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = data[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}