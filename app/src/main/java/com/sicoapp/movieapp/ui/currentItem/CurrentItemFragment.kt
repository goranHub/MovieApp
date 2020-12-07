package com.sicoapp.movieapp.ui.currentItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.response.topRated.TopRated
import com.sicoapp.movieapp.databinding.FragmentCurrentBinding
import com.sicoapp.movieapp.utils.CallbackList
import com.sicoapp.movieapp.utils.ITEM_ID


class CurrentItemFragment : Fragment() {

    private lateinit var binging: FragmentCurrentBinding
    var itemId = 0
    lateinit var callback: CallbackList



    val viewModel by lazy { CurrentItemViewModel(itemId, callback) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(ITEM_ID, -1)?.let {
            itemId = it
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binging = DataBindingUtil.inflate(inflater, R.layout.fragment_current, container, false)


        //databinding K u xml
        //ubaciti jos u currentfrag
        //pposloziti data layer
        //movie od kuma
        //sortiranje button listu
        callback = object : CallbackList {
            override fun listToFragment(responseTopRated: TopRated) {
                binging.data = viewModel
                binging.movie = responseTopRated


                context?.let {
                    Glide.with(it)
                        .load("https://image.tmdb.org/t/p/w185/" + responseTopRated.poster_path)
                        .into(binging.ivImage)
                }

            }
        }



        return binging.root
    }
}