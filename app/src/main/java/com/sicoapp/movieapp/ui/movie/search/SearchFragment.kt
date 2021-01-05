package com.sicoapp.movieapp.ui.movie.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.databinding.FragmentMovieSearchBinding
import com.sicoapp.movieapp.databinding.ItemMovieSearchBinding
import com.sicoapp.movieapp.utils.BindMovie
import com.sicoapp.movieapp.utils.ITEM_ID
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/1/2021
 */

@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject
    lateinit var api: MovieApiService
    lateinit var binding: FragmentMovieSearchBinding
    lateinit var response: List<BindMovie>

    private var postId = 1

    private val viewModel by lazy {
        SearchViewModel(api)
        { postID ->
            val bundleItemId = bundleOf(ITEM_ID to postID)
            findNavController().navigate(
                R.id.action_searchFragment_to_movieDetailsFragment,
                bundleItemId
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieSearchBinding.inflate(inflater)

        binding.imageButtonBack.setOnClickListener { findNavController().popBackStack() }

        init()

        setupSearchView()

        return binding.root
    }

    private fun setupSearchView() {

        binding.searchView.clickSubmitButton { query ->

            viewModel.rxToLiveData(query).observe(viewLifecycleOwner, Observer {
                val movieResponse = it.results
                val movieItemsList = movieResponse.map { BindMovie(it) }
                viewModel.adapter.updateItems(movieItemsList)
            })
        }
    }

    private fun init() {
        binding.recyclerView.adapter = viewModel.adapter
    }

    fun SearchView.clickSubmitButton(clickedBlock: (String) -> Unit) {
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