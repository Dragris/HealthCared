package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcared.R

class CreateDiet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_diet)
    }

    fun goBack(view: View) {
        val intent = Intent(this, DietHome::class.java)
        startActivity(intent)
    }

    fun addDiet(view: View) {
        //THINGS
    }
}