package com.example.healthcared

import android.content.Intent
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_workout_home.*

class WorkoutHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_home)
        initButtons()

    }

    fun goBack(view: View) {
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
    }

    fun planPreview(view: View, string: String){
        val intent = Intent(this, PlanPreview::class.java)
        intent.putExtra("title", string)
        startActivity(intent)
    }

    fun settings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun initButtons(){
        findViewById<Button>(R.id.example_plan).setOnClickListener {
            planPreview(findViewById(R.id.example_plan), findViewById<Button>(R.id.example_plan).text as String)
        }
        findViewById<Button>(R.id.example_plan2).setOnClickListener {
            planPreview(findViewById(R.id.example_plan), findViewById<Button>(R.id.example_plan2).text as String)
        }
    }

    fun createPlan(view: View){
        val intent = Intent(this, CreatePlan::class.java)
        startActivity(intent)
    }
}
