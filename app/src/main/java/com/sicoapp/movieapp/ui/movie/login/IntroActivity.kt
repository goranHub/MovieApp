package com.sicoapp.movieapp.ui.movie.login


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sicoapp.movieapp.R
import kotlinx.android.synthetic.main.activity_intro.*


class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        btn_sign_in.setOnClickListener {
            startActivity(Intent(this@IntroActivity, SignInActivity::class.java))
        }

        btn_sign_up.setOnClickListener {
            startActivity(Intent(this@IntroActivity, SignUpActivity::class.java))
        }
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