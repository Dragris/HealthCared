package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import com.example.healthcared.R
import kotlinx.android.synthetic.main.activity_start_exercise.*
import kotlinx.android.synthetic.main.activity_start_exercise.view.*

class StartExercise : AppCompatActivity() {
    var time:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_exercise)
        val title = intent.getStringExtra("title")
        workout_title.text = title
        val timer: Chronometer = findViewById<Chronometer>(R.id.chrono)
        timer.base = SystemClock.elapsedRealtime()
        timer.start()
    }

    fun goBack(view: View){
        finish()
    }

    fun settings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun pauseTimer(view: View){
        val timer: Chronometer = findViewById<Chronometer>(R.id.chrono)
        val button = findViewById<Button>(R.id.pause)
        if (button.text == "Pause"){
            time = timer.base - SystemClock.elapsedRealtime()
            timer.stop()
            button.text = "Resume"
        } else {
            timer.base = time + SystemClock.elapsedRealtime()
            timer.start()
            button.text = "Pause"
        }
    }

    fun finishTimer(view: View){
        val timer: Chronometer = findViewById<Chronometer>(R.id.chrono)
        val button = findViewById<Button>(R.id.pause)
        timer.stop()
        timer.base = SystemClock.elapsedRealtime()
        button.text = "Start"
        //Finish routine
    }
}
