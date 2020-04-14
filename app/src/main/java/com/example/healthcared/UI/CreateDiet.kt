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
        finish()
    }

    fun addDiet(view: View) {
        //THINGS
    }
}
