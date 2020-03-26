package com.example.healthcared.UI

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcared.R
import kotlinx.android.synthetic.main.activity_diet_view.*

class DietView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_view)
        val title = intent.getStringExtra("title")
        diet_title.setText(title)
    }

    fun goBack(view: View) {
        val intent = Intent(this, DietHome::class.java)
        startActivity(intent)
    }

    fun settings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun showLink(view: View){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=AARyGaYvVI8&t=4s"))
        startActivity(intent)
    }
}
