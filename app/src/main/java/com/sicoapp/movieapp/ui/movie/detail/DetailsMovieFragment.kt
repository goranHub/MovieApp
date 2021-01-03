package com.sicoapp.movieapp.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.hsalf.smileyrating.SmileyRating
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.database.DAOAccess
import com.sicoapp.movieapp.data.response.Movie
import com.sicoapp.movieapp.databinding.FragmentMovieDetailsBinding
import com.sicoapp.movieapp.repository.SmileyRepository
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.DetailsObserver
import com.sicoapp.movieapp.utils.ITEM_ID
import com.sicoapp.movieapp.utils.URL_IMAGE
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    var currentType by Delegates.notNull<Int>()
    var itemId = 0

    var detailsObserver = DetailsObserver()

    @Inject
    lateinit var smileyRepository: SmileyRepository

    private val viewModel by lazy { DetailsViewModel(smileyRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(ITEM_ID, -1)?.let {
            itemId = it
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater)

        liveDataGetMovie(itemId)

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

    /*
    make retrofit call
    the response flowable convert to live data
    and update UI with bindAdapters from DetailsObserver
     */

    private fun liveDataGetMovie(userId: Int) : LiveData<Movie> {
        val source = LiveDataReactiveStreams.fromPublisher(
            MovieApiService.getClient().getByID(userId, API_KEY)
                .subscribeOn(Schedulers.io())
        )

        source.observe(viewLifecycleOwner, {
            detailsObserver.imageUrl = URL_IMAGE + it.posterPath
            detailsObserver.overview = it.overview
            detailsObserver.popularity = it.popularity
            detailsObserver.releaseDate = it.releaseDate
        })
        return source
    }
}