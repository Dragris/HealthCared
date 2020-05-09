package com.example.healthcared.UI

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.healthcared.Controlador
import com.example.healthcared.R


class WorkoutHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_home)
        val layout = findViewById<LinearLayout>(R.id.layout)
        for (i in Controlador.usuario.rutinas){
            val button = Button(this)
            button.width = LinearLayout.LayoutParams.MATCH_PARENT
            button.height = LinearLayout.LayoutParams.WRAP_CONTENT
            button.text = i.rutinaName
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 0, 0, 50)
            button.setBackgroundResource(R.drawable.button_shape)
            button.setOnClickListener { planPreview(i.rutinaName) }
            layout.addView(button, params)
        }
    }

    fun goBack(view: View) {
        finish()
    }

    fun planPreview(string: String){
        val intent = Intent(this, PlanPreview::class.java)
        intent.putExtra("title", string)
        startActivity(intent)
    }

    fun settings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun createPlan(view: View){
        val intent = Intent(this, CreatePlan::class.java)
        startActivity(intent)
        finish()
    }
}
