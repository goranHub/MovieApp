package com.sicoapp.movieapp.ui.movie.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMovieSearchBinding
import com.sicoapp.movieapp.utils.ITEM_ID
import com.sicoapp.movieapp.utils.MEDIATYP
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


/**
 * @author ll4
 * @date 1/1/2021
 */

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    lateinit var binding: FragmentMovieSearchBinding

    var callback = object : SearchCallback {
        override fun openDetails(movieId: Long, mediaTyp: String) {
            val bundlePostIdAndMediaTyp = bundleOf(ITEM_ID to movieId, MEDIATYP to mediaTyp)
            findNavController().navigate(
                R.id.action_searchFragment_to_movieDetailsFragment,
                bundlePostIdAndMediaTyp
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieSearchBinding.inflate(inflater)
        binding.imageButtonBack.setOnClickListener { findNavController().popBackStack() }
        binding.data = viewModel
        binding.callback = callback

        setupSearchView()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    @SuppressLint("CheckResult")
    private fun setupSearchView() {

        fromView()
            .filter{
                if (it.isEmpty()){
                    viewModel.clear()
                    Log.d("emptyString", "emptyString")
                }
                return@filter true}
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                //znaci sa pageId ==1
                viewModel.loadRemoteData(it)
                scrollRecyclerView(it)
            }
    }

    private fun scrollRecyclerView(query: String) {
        binding.recyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //znaci sa pageId koji nije 1
                    viewModel.loadMoreRemoteData(query)
                }
            }
        })
    }

    private fun fromView(): Observable<String> {
        val subject = PublishSubject.create<String>()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                subject.onComplete()
                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                subject.onNext(text)
                return true
            }
        })
        return subject
    }
}
