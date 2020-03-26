package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.healthcared.R

class DietHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_home)
        initButtons()
    }

    fun goBack(view: View) {
        finish()
    }

    fun settings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun createDiet(view: View){
        val intent = Intent(this, CreateDiet::class.java)
        startActivity(intent)
    }

    fun initButtons(){
        findViewById<Button>(R.id.example_plan).setOnClickListener {
            dietView(findViewById(R.id.example_plan), findViewById<Button>(
                R.id.example_plan
            ).text as String)
        }
        findViewById<Button>(R.id.example_plan2).setOnClickListener {
            dietView(findViewById(R.id.example_plan), findViewById<Button>(
                R.id.example_plan2
            ).text as String)
        }
    }

    fun dietView(view: View, string: String){
        val intent = Intent(this, DietView::class.java)
        intent.putExtra("title", string)
        startActivity(intent)
    }
}
