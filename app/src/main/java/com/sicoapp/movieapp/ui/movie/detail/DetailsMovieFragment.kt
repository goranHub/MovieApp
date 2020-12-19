package com.sicoapp.movieapp.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.hsalf.smileyrating.SmileyRating
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMovieDetailsBinding
import com.sicoapp.movieapp.utils.ITEM_ID
import kotlin.properties.Delegates

class DetailsMovieFragment : Fragment() {

    private lateinit var binging: FragmentMovieDetailsBinding
    var currentType by Delegates.notNull<Int>()
    var itemId = 0
    private val viewModel by lazy { DetailsViewModel(itemId) }

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

        binging = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_details,
            container,
            false
        )

        binging.data = viewModel

        val viewModelInstance = viewModel

        binging.smiley.setSmileySelectedListener { type ->
            saveIntoDB(type, viewModelInstance)
        }

        binging.btnCall.setOnClickListener { view ->
            callFromDB(view,viewModelInstance)
        }

        binging.btnDeleteAll.setOnClickListener {
            viewModelInstance.removeDataForThatItem(it.context, itemId)
        }

        return binging.root
    }

    private fun saveIntoDB(type: SmileyRating.Type, viewModelInstance : DetailsViewModel) {
        currentType = type.rating
        binging.btnSave.setOnClickListener { view ->
            context?.let {
                viewModelInstance.insertData(it, itemId, currentType)
            }
        }
    }

    private fun callFromDB(view: View,  viewModelInstance : DetailsViewModel){
        viewModelInstance.getMovieRatingDetails(view.context, itemId)!!
            .observe(viewLifecycleOwner, { movieRatingTabelModel ->
                if (movieRatingTabelModel != null) {
                    binging.smiley.setRating(movieRatingTabelModel.rating)
                }
            })
    }
}