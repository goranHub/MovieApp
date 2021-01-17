package com.sicoapp.movieapp.ui.movie.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.ActivityEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_entry.*

/**
 * @author ll4
 * @date 1/14/2021
 */
@AndroidEntryPoint
class EntryActivity : AppCompatActivity(){

    lateinit var navController: NavController
    lateinit var bottomNav: BottomNavigationView
    private lateinit var binding: ActivityEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        bottomNav = bottomNavigationView

        navController = findNavController(R.id.nav_host_fragment_entry)

        setupBottomNavigation()
    }

    override fun onResume() {
        super.onResume()
/*
        this.supportActionBar!!.hide()
*/
    }

    override fun onStop() {
        super.onStop()
/*
        this.supportActionBar!!.show()
*/
    }

    private fun setupBottomNavigation() {
        bottomNav.setupWithNavController(navController)
    }
}
