package com.sicoapp.movieapp.ui.movie.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.sicoapp.movieapp.MainActivity
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.firebase.FireStoreClass

/**
 * @author ll4
 * @date 1/14/2021
 */
class EntryActivity  : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        Handler().postDelayed({

            val currentUserID = FireStoreClass().getCurrentUserID()

            if (currentUserID.isNotEmpty()) {

                startActivity(Intent(this@EntryActivity, MainActivity::class.java))
            } else {

                startActivity(Intent(this@EntryActivity, IntroActivity::class.java))
            }
            finish()
        }, 2500)


    }


}
