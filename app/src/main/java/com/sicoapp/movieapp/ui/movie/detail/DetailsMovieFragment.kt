package com.sicoapp.movieapp.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hsalf.smileyrating.SmileyRating
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMovieDetailsBinding
import com.sicoapp.movieapp.utils.ITEM_ID
import kotlin.properties.Delegates

class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    var currentType by Delegates.notNull<Int>()
    var itemId = 0

    private lateinit var viewModel : DetailsViewModel
    private lateinit var viewModelFactory : DetailViewModelFactory

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

        viewModelFactory = DetailViewModelFactory(itemId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)

        binding.data = viewModel.detailsObserver

        val viewModelInstance = viewModel

        binding.smiley.setSmileySelectedListener { type ->
            saveIntoDB(type, viewModelInstance)
        }

        binding.btnCall.setOnClickListener { view ->
            callFromDB(view,viewModelInstance)
        }

        binding.btnDeleteAll.setOnClickListener {
            viewModelInstance.removeDataForThatItem(it.context, itemId)
        }
        return binding.root
    }

    private fun saveIntoDB(type: SmileyRating.Type, viewModelInstance : DetailsViewModel) {
        currentType = type.rating
        binding.btnSave.setOnClickListener {
            context?.let {
                viewModelInstance.insertData(it, itemId, currentType)
            }
        }
    }

    private fun callFromDB(view: View,  viewModelInstance : DetailsViewModel){
        viewModelInstance.getSavedSmileyDetails(view.context, itemId)!!
            .observe(viewLifecycleOwner, { movieRatingTabelModel ->
                if (movieRatingTabelModel != null) {
                    binding.smiley.setRating(movieRatingTabelModel.rating)
                }
            })
    }
}