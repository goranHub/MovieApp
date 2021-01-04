package com.sicoapp.movieapp.ui.movie.now

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.R
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.databinding.FragmentMovieSearchBinding
import com.sicoapp.movieapp.utils.BindMovie
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
    lateinit var response : List<BindMovie>
    private var query = ""

    private val viewModel by lazy {
        SearchViewModel(api)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieSearchBinding.inflate(inflater)

        binding.imageButtonBack.setOnClickListener { findNavController().popBackStack() }

        init()

        setupSearchView()

        return binding.root
    }



    private fun setupSearchView() {
        (binding.searchView.findViewById(R.id.search_src_text) as TextView).setTextColor(Color.RED)

        binding.searchView.clickSubmitButton { query ->

            viewModel.rxToLiveData(query).observe( viewLifecycleOwner, Observer {
                val movieResponse = it.results
                val movieItemsList = movieResponse.map { BindMovie(it) }
                viewModel.adapter.addMovies(movieItemsList)
            })
        }
    }

    fun init() {
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