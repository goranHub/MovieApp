package com.sicoapp.movieapp.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMoviePopularBinding
import com.sicoapp.movieapp.ui.BaseFragment
import com.sicoapp.movieapp.EntryActivity
import com.sicoapp.movieapp.ui.topmovie.TopMovieCallback
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.ITEM_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_entry.*

/**
 * @author ll4
 * @date 1/1/2021
 */
@AndroidEntryPoint
class PopularMovieFragment : BaseFragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: FragmentMoviePopularBinding

    private val viewModel: PopularViewModel by viewModels()

    val callback = object : TopMovieCallback {
        override fun openDetails(movieId: Long) {
            val bundleItemId = bundleOf(ITEM_ID to movieId)
            findNavController().navigate(
                R.id.action_popularMovieFragment_to_movieDetailsFragment,
                bundleItemId
            )
        }

        override fun openCrew(crewId: Long) {
            val bundleCrewId = bundleOf(CREW_ID to crewId)
            findNavController().navigate(
                R.id.action_popularMovieFragment_to_crewMovieFragment,
                bundleCrewId
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMoviePopularBinding.inflate(inflater)
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

            R.id.list_movie_saved -> {
                findNavController().navigate(
                    R.id.action_popularMovieFragment_to_saveFragment
                )
            }

            R.id.sign_out -> {
                FirebaseAuth.getInstance().signOut()
                findNavController().navigate(
                    R.id.action_popularMovieFragment_to_introFragment)
            }
        }

        (activity as EntryActivity).drawer_layout?.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setNavigationViewListener() {
        val navigationView = (activity as EntryActivity).navigation_view
        navigationView.setNavigationItemSelectedListener(this)
    }


    override fun onResume() {
        super.onResume()
       // (activity as EntryActivity?)!!.supportActionBar?.show()
    }

    override fun onStop() {
        super.onStop()
    }
}