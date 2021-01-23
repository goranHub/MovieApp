package com.sicoapp.movieapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.sicoapp.movieapp.databinding.ActivityEntryBinding
import com.sicoapp.movieapp.databinding.DrawerHeaderBinding
import com.sicoapp.movieapp.domain.Repository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * @author ll4
 * @date 1/14/2021
 */
@AndroidEntryPoint
class EntryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var repository: Repository


    lateinit var navController: NavController
    lateinit var bottomNav: BottomNavigationView
    lateinit var binding: ActivityEntryBinding
    lateinit var bindingDrawerHeader: DrawerHeaderBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.topMovieFragment,
                R.id.popularMovieFragment,
                R.id.searchFragment,
                R.id.savedFragment,
                R.id.movieDetailsFragment
            ), drawerLayout
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_entry)

        bottomNav = binding.bottomNavigationView

        bottomNav.visibility = View.GONE

        navController = findNavController(R.id.nav_host_fragment_entry)

        toolbar = binding.toolbar

        setupBottomNavigation()

        setupNav()

        setupNavigation()

    }

    private fun setupNavigation() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        drawerLayout = binding.drawerLayout
        val navigationView = binding.navigationView
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true
        drawerLayout.closeDrawers()
        when (menuItem.itemId) {
            R.id.my_profile -> navController.navigate(R.id.myProfileFragment)
            R.id.movies -> navController.navigate(R.id.topMovieFragment)
            R.id.saved -> navController.navigate(R.id.savedFragment)
            R.id.sign_out -> navController.navigate(R.id.introFragment)
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupNav() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.topMovieFragment -> showBottomNav()
                R.id.popularMovieFragment -> showBottomNav()
                R.id.searchFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    private fun setupBottomNavigation() {
        bottomNav.setupWithNavController(navController)
    }

    private fun showBottomNav() {
        toolbar.visibility = View.VISIBLE
        bottomNav.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        toolbar.visibility = View.GONE
        bottomNav.visibility = View.GONE
    }
}
