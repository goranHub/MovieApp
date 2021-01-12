package com.sicoapp.movieapp.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hsalf.smileyrating.SmileyRating
import com.sicoapp.movieapp.databinding.FragmentMovieDetailsBinding
import com.sicoapp.movieapp.utils.ITEM_ID
import com.sicoapp.movieapp.utils.MEDIATYP
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    var currentType by Delegates.notNull<Int>()
    var movieId = 0L
    var mediaTyp = ""

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getLong(ITEM_ID, -1)?.let {
            movieId = it
        }
        arguments?.getString(MEDIATYP, "")?.let {
            mediaTyp = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieDetailsBinding.inflate(inflater)

        if ((mediaTyp == "movie") or (mediaTyp == "")) {
            updateUIMovie(movieId)
        } else {
            updateUITv(movieId)
        }

        binding.data = viewModel.bindDetails


            saveIntoDB()


        binding.btnCall.setOnClickListener {
            callFromDB()
        }

        binding.btnDeleteAll.setOnClickListener {
            viewModel.removeDataForThatItem(movieId.toInt())
        }
        return binding.root
    }

    private fun saveIntoDB() {
        binding.smiley.setSmileySelectedListener {
            viewModel.insertData(movieId.toInt(), it.rating)
        }
    }

    private fun callFromDB() {
        viewModel.getSavedSmileyDetails(movieId.toInt())
            .observe(viewLifecycleOwner, { movieRatingTableModel ->
                if (movieRatingTableModel != null) {
                    binding.smiley.setRating(movieRatingTableModel.rating)
                }
            })
    }

    private fun updateUITv(movieId: Long) {
        viewModel.loadRemoteDataTv(movieId)
    }

    private fun updateUIMovie(movieId: Long) {
        viewModel.loadRemoteDataMovie(movieId)
    }
}