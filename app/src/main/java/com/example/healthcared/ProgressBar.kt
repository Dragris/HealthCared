package com.example.healthcared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.example.healthcared.UI.WorkoutHome

class ProgressBar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)
        var title = intent.getStringExtra("title")
        findViewById<TextView>(R.id.text).text = title
        var handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, WorkoutHome::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }

    override fun onBackPressed() {}
}
