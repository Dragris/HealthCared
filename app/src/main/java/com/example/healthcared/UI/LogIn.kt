package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcared.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

//import kotlinx.android.synthetic.main.activity_signup1.*


class LogIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

    }

    fun login(view: View) {

        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
    }

    fun signup1(view: View) {
        val intent = Intent(this, Singup1::class.java)
        startActivity(intent)
    }



}
