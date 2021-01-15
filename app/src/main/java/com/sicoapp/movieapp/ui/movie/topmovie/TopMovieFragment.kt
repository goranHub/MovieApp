package com.sicoapp.movieapp.ui.movie.topmovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.firebase.FireStoreClass
import com.sicoapp.movieapp.databinding.FragmentMovieTopBinding
import com.sicoapp.movieapp.ui.movie.BaseFragment
import com.sicoapp.movieapp.ui.movie.login.IntroFragment
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.ITEM_ID
import com.sicoapp.movieapp.utils.USER_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class TopMovieFragment : BaseFragment(), NavigationView.OnNavigationItemSelectedListener {

    private val viewModel: TopMovieViewModel by viewModels()

    private lateinit var binding: FragmentMovieTopBinding

    /*
    instead use paramters in ViewModel constructor that are connect Adapter with Fragment
    we connect now the callback with Adapter trough xml and BindigAdapter
     */

    val callback = object : TopMovieCallback {
        override fun openDetails(movieId: Long) {
            val bundleItemId = bundleOf(ITEM_ID to movieId)
            findNavController().navigate(
                R.id.action_movieListFragment_to_movieDetailsFragment,
                bundleItemId)
        }

        override fun openCrew(crewId: Long) {
            val bundleCrewId = bundleOf(CREW_ID to crewId)
            findNavController().navigate(
                R.id.action_movieListFragment_to_crewMovieFragment,
                bundleCrewId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieTopBinding.inflate(inflater)

        binding.data = viewModel

        binding.callback = callback

        scrollRecyclerView()

        setNavigationViewListener()
        return binding.root
    }

    private fun scrollRecyclerView() {
        binding.recylerViewFragmentTopMovie.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadRemoteData()
                }
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.my_profile -> {
            }

            R.id.list_movie_saved->{
                findNavController().navigate(
                    R.id.action_topMovieFragment_to_itemFragment)
            }

            R.id.sign_out -> {
                val currentUserID = FireStoreClass().getCurrentUserID()
                val userIdBundle = bundleOf(USER_ID to currentUserID)
                findNavController().navigate(
                    R.id.action_topMovieFragment_to_start_navigation, userIdBundle)
            }
        }
        activity?.drawer_layout?.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setNavigationViewListener() {
        val navigationView = activity?.navigation_view
        navigationView?.setNavigationItemSelectedListener(this)
    }
}