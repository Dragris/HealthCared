package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.healthcared.R

class animescreen : AppCompatActivity() {
lateinit var handler : Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animescreen)
        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            //TODO incluir las cargas de contenido incial
            finish()
        }, 3000)


    }
}
