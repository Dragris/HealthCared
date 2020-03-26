package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcared.R

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    fun goBack(view: View){
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
    }

    fun settings (view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }
    fun changeProfileImage(view: View){
        //Change profile picture.
    }
}
