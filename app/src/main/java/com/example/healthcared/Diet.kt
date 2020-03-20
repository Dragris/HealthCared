package com.example.healthcared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Diet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)

    }

    fun goBack(view: View) {
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
    }
    fun settings(view: View) {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }
}

