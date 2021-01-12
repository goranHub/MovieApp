package com.sicoapp.movieapp.ui.movie.detail

import android.graphics.Color
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

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
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

        callFromDB()

        binding.btnDeleteAll.setOnClickListener {
            deleteFromDB()
        }

        return binding.root
    }

    private fun deleteFromDB() {
        viewModel.removeRatingForMovie(movieId.toInt())
        binding.smiley.setFaceBackgroundColor(SmileyRating.Type.TERRIBLE, Color.BLACK)
        binding.smiley.setFaceBackgroundColor(SmileyRating.Type.GOOD, Color.BLACK)
        binding.smiley.setFaceBackgroundColor(SmileyRating.Type.GREAT, Color.BLACK)
        binding.smiley.setFaceBackgroundColor(SmileyRating.Type.BAD, Color.BLACK)
        binding.smiley.setFaceBackgroundColor(SmileyRating.Type.OKAY, Color.BLACK)
    }

    private fun saveIntoDB() {
        binding.smiley.setSmileySelectedListener {
            viewModel.insertData(movieId.toInt(), it.rating)
        }
    }

    private fun callFromDB() {

        viewModel.getSavedSmileyDetails(movieId.toInt())
            .observe(viewLifecycleOwner, { response ->
                if (response != null) {
                    val rating = response.rating
                    setFaceBackgroundColor(rating)
                }
            })
    }

    private fun setFaceBackgroundColor(
        rating: Int
    ) {
        lateinit var type: SmileyRating.Type
        var color: Int
        if (rating == 1) {
            type = SmileyRating.Type.TERRIBLE
            color = Color.YELLOW
            binding.smiley.setRating(rating, true)
            binding.smiley.setFaceBackgroundColor(type, color)
        }
        if (rating == 2) {
            type = SmileyRating.Type.BAD
            color = Color.YELLOW
            binding.smiley.setRating(rating, true)
            binding.smiley.setFaceBackgroundColor(type, color)
        }
        if (rating == 3) {
            type = SmileyRating.Type.OKAY
            color = Color.YELLOW
            binding.smiley.setRating(rating, true)
            binding.smiley.setFaceBackgroundColor(type, color)
        }
        if (rating == 4) {
            type = SmileyRating.Type.GOOD
            color = Color.YELLOW
            binding.smiley.setRating(rating, true)
            binding.smiley.setFaceBackgroundColor(type, color)
        }
        if (rating == 5) {
            type = SmileyRating.Type.GREAT
            color = Color.YELLOW
            binding.smiley.setRating(rating, true)
            binding.smiley.setFaceBackgroundColor(type, color)
        }
    }

    private fun updateUITv(movieId: Long) {
        viewModel.loadRemoteDataTv(movieId)
    }

    private fun updateUIMovie(movieId: Long) {
        viewModel.loadRemoteDataMovie(movieId)
    }
}


