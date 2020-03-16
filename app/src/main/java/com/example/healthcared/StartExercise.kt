package com.example.healthcared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_start_exercise.*

class StartExercise : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_exercise)
        val title = intent.getStringExtra("title")
        workout_title.setText(title)
    }

    fun goBack(view: View){
        val intent = Intent(this, PlanPreview::class.java)
        startActivity(intent)
    }

    fun goProfile(view: View){
        //val intent = Intent(this, profile::class.java)
        //startActivity(intent)
    }

    fun pauseTimer(view: View){
        //Resume and pause timer
    }

    fun finishTimer(view: View){
        //Finish routine
    }
}
