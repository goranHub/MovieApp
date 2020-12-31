package com.sicoapp.movieapp.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hsalf.smileyrating.SmileyRating
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.database.DAOAccess
import com.sicoapp.movieapp.databinding.FragmentMovieDetailsBinding
import com.sicoapp.movieapp.repository.SmileyRepository
import com.sicoapp.movieapp.utils.ITEM_ID
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    var currentType by Delegates.notNull<Int>()
    var itemId = 0

    @Inject
    lateinit var dao : DAOAccess

    @Inject
    lateinit var smileyRepository: SmileyRepository

    private val viewModel by lazy { DetailsViewModel(itemId, smileyRepository) }

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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_details,
            container,
            false
        )

        binding.data = viewModel.detailsObserver

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
}