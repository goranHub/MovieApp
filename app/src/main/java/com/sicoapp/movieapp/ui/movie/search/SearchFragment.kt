package com.sicoapp.movieapp.ui.movie.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMovieSearchBinding
import com.sicoapp.movieapp.utils.ITEM_ID
import com.sicoapp.movieapp.utils.MEDIATYP
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author ll4
 * @date 1/1/2021
 */

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    lateinit var binding: FragmentMovieSearchBinding

    var callback = object : SearchCallback {
        override fun openDetails(movieId: Long, mediaTyp: String)  {
            val bundlePostIdAndMediaTyp = bundleOf(ITEM_ID to movieId, MEDIATYP to mediaTyp)
            findNavController().navigate(
                R.id.action_searchFragment_to_movieDetailsFragment,
                bundlePostIdAndMediaTyp)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieSearchBinding.inflate(inflater)
        binding.imageButtonBack.setOnClickListener { findNavController().popBackStack() }
        binding.data = viewModel
        binding.callback = callback

        setupSearchView()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    private fun setupSearchView() {
        binding.searchView.clickSubmitButton { query ->
            viewModel.loadRemoteData(query)
            scrollRecyclerView(query)
        }
    }

    private fun scrollRecyclerView(query: String) {
        binding.recyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadRemoteData(query)
                }
            }
        })
    }

    private fun SearchView.clickSubmitButton(clickedBlock: (String) -> Unit) {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                clickedBlock(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                return true
            }
        })
    }
}