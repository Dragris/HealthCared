package com.example.healthcared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Inicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
    }

    fun history (view: View){
        val intent = Intent(this, History::class.java)
        startActivity(intent)
    }

    fun workouts (view: View){
        val intent = Intent(this, WorkoutHome::class.java)
        startActivity(intent)
    }
}
