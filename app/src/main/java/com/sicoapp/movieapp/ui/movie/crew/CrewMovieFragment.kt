package com.sicoapp.movieapp.ui.movie.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.databinding.FragmentMovieCrewBinding
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.Injection

class CrewMovieFragment() : Fragment() {

    val service = Injection.provideMovieApiService()
    private lateinit var binding: FragmentMovieCrewBinding
    var crewId = 0
    val movieApiService = MovieApiService
    private val viewModel by lazy { CrewViewModel(crewId, service.getClient()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(CREW_ID, -1)?.let {
            crewId = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_crew, container, false)
        binding.data = viewModel
        return binding.root
    }
}