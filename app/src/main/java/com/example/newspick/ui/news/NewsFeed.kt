package com.example.newspick.ui.news

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import androidx.work.WorkInfo
import com.example.newspick.R
import com.example.newspick.adapter.NewsAdapter
import com.example.newspick.data.model.Article
import com.example.newspick.databinding.NewsFeedFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NewsFeed : Fragment() {
    private val viewModel: NewsFeedViewModel by viewModels()
    private lateinit var binding:NewsFeedFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.news_feed_fragment,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch()
        viewModel.articles.observe(viewLifecycleOwner,{
            val newsAdapter=NewsAdapter(it,viewModel)
            if(!it.isNullOrEmpty()) {
                binding.newsFeedViewPager.apply {
                    adapter = newsAdapter
                    orientation = ViewPager2.ORIENTATION_VERTICAL
                }
            }

        })
        viewModel.outputWorkInfo.observe(viewLifecycleOwner,workInfoObserver())

        binding.itemRefresh.setOnRefreshListener {
            viewModel.fetch()
            binding.itemRefresh.isRefreshing=false
        }
    }

    private fun workInfoObserver(): Observer<MutableList<WorkInfo>> {
        return Observer {
            if(it.isNullOrEmpty()){
                return@Observer
            }
            val workInfo=it.last()
            if(workInfo.state.isFinished){
                val outputImageUri=workInfo.outputData.getString("IMAGE_URI")
                if (!outputImageUri.isNullOrEmpty()){
                    viewModel.insertBookmark(outputImageUri)
                }
            }
        }
    }

}