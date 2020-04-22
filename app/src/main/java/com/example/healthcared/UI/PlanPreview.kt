package com.example.healthcared.UI

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.healthcared.Controlador
import com.example.healthcared.Modelo.Rutina
import com.example.healthcared.R
import kotlinx.android.synthetic.main.activity_plan_preview.*
import java.time.LocalDateTime
import java.util.*

class PlanPreview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_preview)
        val title = intent.getStringExtra("title")
        workout_title.setText(title)

        val dia = getDayofWeek()
        var rutina = Controlador.usuario.findRutinaByName(title).getDayByDay(1)
        val layout = findViewById<LinearLayout>(R.id.layout)

        if (Controlador.usuario.findRutinaByName(title).obj == "Cardio"){
            val textView = TextView(this)
            val text2 = TextView(this)
            textView.text = "Make as much resps as you can"
            text2.text = "Rest as much as you need"
            textView.gravity = android.view.Gravity.CENTER
            text2.gravity = android.view.Gravity.CENTER
            layout.addView(textView)
            layout.addView(text2)
        }
        for (i in rutina){
            var parent = LinearLayout(this)
            parent.orientation = LinearLayout.HORIZONTAL
            parent.gravity = android.view.Gravity.NO_GRAVITY

            var texts = LinearLayout(this)
            texts.orientation = LinearLayout.VERTICAL
            texts.gravity = android.view.Gravity.NO_GRAVITY

            var image = ImageView(this) //Exercise IMG
            image.id = View.generateViewId()
            image.setOnClickListener { showLink(i.youtubeLink) }
            image.setImageResource(R.drawable.common_google_signin_btn_icon_light)
            val name = TextView(this)
            name.text = i.ExerciceName
            texts.addView(name)
            val reps = TextView(this)
            reps.text = "Reps: " + Controlador.usuario.findRutinaByName(title).reps.toString()
            texts.addView(reps)
            if (Controlador.usuario.findRutinaByName(title).obj != "Cardio"){
                val sets = TextView(this)
                sets.text = "Sets: " + Controlador.usuario.findRutinaByName(title).sets.toString()
                texts.addView(sets)
                val rest = TextView(this)
                rest.text = "Rest (Secs): " + Controlador.usuario.findRutinaByName(title).rest.toString()
                texts.addView(rest)
            }


            parent.addView(image)
            parent.addView(texts)
            layout.addView(parent)
            Log.v("Rutina", rutina.toString())
        }
    }

    fun goBack(view: View) {
        finish()
    }

    fun settings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun startExercise(view: View){
        val intent = Intent(this, StartExercise::class.java)
        intent.putExtra("title", findViewById<TextView>(R.id.workout_title).text as String)
        startActivity(intent)
    }

    fun showLink(link: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }

    fun getDayofWeek(): Int {
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)-1
        return day
    }
}
