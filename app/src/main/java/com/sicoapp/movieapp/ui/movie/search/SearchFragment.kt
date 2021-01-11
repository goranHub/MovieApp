package com.sicoapp.movieapp.ui.movie.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.databinding.FragmentMovieSearchBinding
import com.sicoapp.movieapp.ui.movie.search.adapter.SearchAdapter
import com.sicoapp.movieapp.utils.ITEM_ID
import com.sicoapp.movieapp.utils.MEDIATYP
import com.sicoapp.movieapp.utils.factory.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/1/2021
 */

@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject
    lateinit var repository: RemoteRepository

    private lateinit var viewModel: SearchViewModel

    private lateinit var viewModelFactory: ViewModelFactory

    lateinit var binding: FragmentMovieSearchBinding

    val adapter = SearchAdapter { postID, mediaTyp ->
        val bundlePostIdAndMediaTyp = bundleOf(ITEM_ID to postID, MEDIATYP to mediaTyp)
        findNavController().navigate(
            R.id.action_searchFragment_to_movieDetailsFragment,
            bundlePostIdAndMediaTyp
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModelFactory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        binding = FragmentMovieSearchBinding.inflate(inflater)

        binding.imageButtonBack.setOnClickListener { findNavController().popBackStack() }

        binding.data = this

        setupSearchView()

        return binding.root
    }


    private fun setupSearchView() {
        binding.searchView.clickSubmitButton { query ->

            loadMulti(query)

            scrollRecyclerView(query)

        }
    }

    private fun scrollRecyclerView(query: String) {
        binding.recyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadMulti(query)
                }
            }
        })
    }

    private fun loadMulti(query: String) {
        viewModel.rxMulti(query).observe(viewLifecycleOwner, { multi1 ->
            val movieResponse = multi1.results
            val movieItemsList = movieResponse.map { BindMulti(it) }
            adapter.updateItems(movieItemsList)
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