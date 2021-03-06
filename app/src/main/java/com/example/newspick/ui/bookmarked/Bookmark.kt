package com.example.newspick.ui.bookmarked

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newspick.R
import com.example.newspick.adapter.BookmarkAdapter
import com.example.newspick.databinding.BookmarkFragmentBinding
import com.example.newspick.databinding.BookmarkedItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Bookmark : Fragment() {

    private  val viewModel: BookmarkViewModel by viewModels()
    private lateinit var binding: BookmarkFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.bookmark_fragment,container,false)
        viewModel.fetchBookmarks()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookmarkAdapter=BookmarkAdapter(viewModel,BookmarkAdapter.CallBackClickListener{
            Log.d("yolo",it.title.toString())
        })
        viewModel.bookmarkArticles.observe(viewLifecycleOwner,{
            if(it.isNullOrEmpty()){
                binding.emptyCard.visibility=View.VISIBLE
            }else{
                binding.emptyCard.visibility=View.GONE
            }
            binding.bookmarkRecyclerView.apply {
                layoutManager=LinearLayoutManager(activity)
                adapter=bookmarkAdapter.apply {
                    submitList(it)
                }
            }
        })



    }

}