package com.example.healthcared

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_plan_preview.*

class PlanPreview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_preview)
        val title = intent.getStringExtra("title")
        workout_title.setText(title)
    }

    fun goBack(view: View) {
        val intent = Intent(this, WorkoutHome::class.java)
        startActivity(intent)
    }

    fun goProfile(view: View){
        //val intent = Intent(this, profile::class.java)
        //startActivity(intent)
    }

    fun startExercise(view: View){
        val intent = Intent(this, StartExercise::class.java)
        intent.putExtra("title", findViewById<TextView>(R.id.workout_title).text as String)
        startActivity(intent)
    }

    fun showLink(view: View){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=AARyGaYvVI8&t=4s"))
        startActivity(intent)
    }
}
