package com.example.healthcared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    fun goBack(view: View){
        val intent = Intent(this, WorkoutHome::class.java)
        startActivity(intent)
    }

    fun changeProfileImage(view: View){
        //Change profile picture.
    }
}
