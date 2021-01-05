package com.sicoapp.movieapp.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hsalf.smileyrating.SmileyRating
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.databinding.FragmentMovieDetailsBinding
import com.sicoapp.movieapp.repository.SmileyRepository
import com.sicoapp.movieapp.utils.DetailsObserver
import com.sicoapp.movieapp.utils.ITEM_ID
import com.sicoapp.movieapp.utils.MEDIATYP
import com.sicoapp.movieapp.utils.URL_IMAGE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    var currentType by Delegates.notNull<Int>()
    var itemId = 0
    var mediaTyp = ""

    var detailsObserver = DetailsObserver()

    @Inject
    lateinit var smileyRepository: SmileyRepository

    @Inject
    lateinit var api: MovieApiService

    private val viewModel by lazy { DetailsViewModel(api, itemId, mediaTyp, smileyRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(ITEM_ID, -1)?.let {
            itemId = it
        }
        arguments?.getString(MEDIATYP, "")?.let{
            mediaTyp = it
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater)

        if(mediaTyp.equals("movie") or mediaTyp.equals("") ){
            updateUIMovie()
        }else{
            updateUITv()
        }

        binding.data = detailsObserver

        binding.smiley.setSmileySelectedListener { type ->
            saveIntoDB(type, viewModel)
        }

        binding.btnCall.setOnClickListener { view ->
            callFromDB(viewModel)
        }

        binding.btnDeleteAll.setOnClickListener {
            viewModel.removeDataForThatItem(itemId)
        }
        return binding.root
    }


    private fun saveIntoDB(type: SmileyRating.Type, viewModelInstance : DetailsViewModel) {
        currentType = type.rating
        binding.btnSave.setOnClickListener {
            context?.let {
                viewModelInstance.insertData(itemId, currentType)
            }
        }
    }

    private fun callFromDB(viewModelInstance : DetailsViewModel){
        viewModelInstance.getSavedSmileyDetails(itemId)
            .observe(viewLifecycleOwner, { movieRatingTableModel ->
                if (movieRatingTableModel != null) {
                    binding.smiley.setRating(movieRatingTableModel.rating)
                }
            })
    }

    private fun updateUIMovie(){
        viewModel.lifeDataMovie().observe(viewLifecycleOwner, {
            detailsObserver.imageUrl = URL_IMAGE + it.posterPath
            detailsObserver.overview = it.overview
            detailsObserver.popularity = it.popularity
            detailsObserver.releaseDate = it.releaseDate
        })
    }

    private fun updateUITv(){
        viewModel.lifeDataTv().observe(viewLifecycleOwner, {
            detailsObserver.imageUrl = URL_IMAGE + it.poster_path
            detailsObserver.overview = it.overview
            detailsObserver.popularity = it.type
            detailsObserver.releaseDate = it.first_air_date
        })
    }

}