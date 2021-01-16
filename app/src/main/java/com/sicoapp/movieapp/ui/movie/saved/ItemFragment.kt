package com.sicoapp.movieapp.ui.movie.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.api.ApiServisFlow
import com.sicoapp.movieapp.data.database.SmileyRatingTableModel
import com.sicoapp.movieapp.data.repository.SmileyRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class ItemFragment : Fragment() {

    @Inject
    lateinit var smileyRepository: SmileyRepository
    @Inject
    lateinit var api: ApiServisFlow

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = SavedAdapter(
                    runBlocking {
                    getSaved()
                },api)
            }
        }
        return view
    }

    private suspend fun getSaved(): List<SmileyRatingTableModel> {
        var saved = smileyRepository.getSaved()
        return saved.distinctBy { it.itemId }
    }
}