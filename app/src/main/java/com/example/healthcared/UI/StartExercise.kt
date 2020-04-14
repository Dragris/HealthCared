package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcared.R
import kotlinx.android.synthetic.main.activity_start_exercise.*

class StartExercise : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_exercise)
        val title = intent.getStringExtra("title")
        workout_title.setText(title)
    }

    fun goBack(view: View){
        finish()
    }

    fun settings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun pauseTimer(view: View){
        //Resume and pause timer
    }

    fun finishTimer(view: View){
        //Finish routine
    }
}
