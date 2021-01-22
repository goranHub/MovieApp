package com.sicoapp.movieapp.ui.saved

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.sicoapp.movieapp.data.database.SmileyRatingEntity
import com.sicoapp.movieapp.databinding.FragmentSavedListBinding
import com.sicoapp.movieapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


@AndroidEntryPoint
class SavedFragment : BaseFragment() {

    private lateinit var binding: FragmentSavedListBinding
    private val viewModel: SavedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSavedListBinding.inflate(inflater)
        binding.listMovieSaved.layoutManager = GridLayoutManager(context, 2)

            viewModel
            .singleSavedList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : SingleObserver<List<SmileyRatingEntity>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(list: List<SmileyRatingEntity>) {
                       val listSmiley = list.distinctBy { it.itemId }
                        viewModel.loadRemoteData(listSmiley)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", "${e.stackTrace}")
                    }
                }
            )

        binding.listMovieSaved.adapter = viewModel.adapter
        return binding.root
    }
}