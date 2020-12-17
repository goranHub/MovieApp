package com.sicoapp.movieapp.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMovieDetailsBinding
import com.sicoapp.movieapp.utils.ITEM_ID
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlin.properties.Delegates

class DetailsMovieFragment : Fragment() {

    private lateinit var binging: FragmentMovieDetailsBinding
    var currentType by Delegates.notNull<Int>()
    var itemId = 0

    private val viewModel by lazy { DetailsViewModel(itemId) }
    val viewModelInstance = viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(ITEM_ID, -1)?.let {
            itemId = it
        }


    }

    //Spremis koji je film i koji je rating i kad otvoris taj film da vidi imal za njeg rating

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

        binging.data = viewModelInstance

        var currentType = binging.smiley.selectedSmiley.rating

        /* kada je kreiran view izvuci iz baze za njega rating smile
         */


        binging.smiley.setSmileySelectedListener { type ->
            status.text = type.toString()
            currentType = type.rating

                context?.let {
                    viewModelInstance.insertData(it, itemId, currentType)
                }


        }

        binging.btnReadLogin.setOnClickListener {

            viewModelInstance.getMovieRatingDetails(it.context, itemId)!!.observe(viewLifecycleOwner, Observer {

                if (it == null) {
                    lblUseraname.text = "- - -"
                    lblPassword.text = "- - -"
                }
                else {
                    lblUseraname.text = it.itemId.toString()
                    lblPassword.text = it.rating.toString()

                }

                context?.let { context ->
                    viewModelInstance.getMovieRatingDetails(context, itemId)
                        ?.observe(viewLifecycleOwner, Observer {
                            currentType = it?.rating!!
                        })
                }
            })
        }



        return binging.root
    }
}