package com.sicoapp.movieapp.ui.movie.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.sicoapp.movieapp.MainActivity
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.firebase.FireStoreClass

/**
 * @author ll4
 * @date 1/14/2021
 */
class EntryActivity : AppCompatActivity(){

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        navController = findNavController(R.id.nav_host_fragment_entry)
    }

    override fun onResume() {
        super.onResume()
        this.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        this.supportActionBar!!.show()
    }
}
