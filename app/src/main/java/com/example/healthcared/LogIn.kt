package com.example.healthcared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup1.*


class LogIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

    }

    fun login(view: View) {

        val intent = Intent(this,Inicio::class.java)
        startActivity(intent)
    }

    fun action1(view: View) {
        val intent = Intent(this,Singup1::class.java)
        startActivity(intent)
    }




}
