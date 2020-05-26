package com.example.healthcared.UI

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
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
        var rutina = Controlador.usuario.findRutinaByName(title).getDayByDay(dia+1)
        val layout = findViewById<LinearLayout>(R.id.layout)

        if (Controlador.usuario.findRutinaByName(title).obj == "Cardio"){
            var img = ImageView(this)
            img.setImageResource(R.drawable.cardio)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(0,0,0,30)
            val textView = TextView(this)
            val text2 = TextView(this)
            textView.text = "Make as much reps as you can"
            text2.text = "Rest as much as you need"
            textView.gravity = android.view.Gravity.CENTER
            text2.gravity = android.view.Gravity.CENTER
            layout.addView(img, params)
            layout.addView(textView)
            layout.addView(text2)
        } else if (Controlador.usuario.findRutinaByName(title).obj == "Toy Gordo"){
            var img = ImageView(this)
            img.setImageResource(R.drawable.wl)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            layout.addView(img, params)
        } else if (Controlador.usuario.findRutinaByName(title).obj == "Fuelsa") {
            var img = ImageView(this)
            img.setImageResource(R.drawable.fuelsa)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            layout.addView(img, params)
        } else {
            var img = ImageView(this)
            img.setImageResource(R.drawable.bulk)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            layout.addView(img, params)
        }

        if (rutina.size == 1) {
            findViewById<ImageButton>(R.id.start).visibility = View.INVISIBLE
        } else {
            findViewById<ImageButton>(R.id.start).visibility = View.VISIBLE
        }
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(100,0,0,30)
        for (i in rutina){
            var parent = LinearLayout(this)
            parent.orientation = LinearLayout.HORIZONTAL
            parent.gravity = android.view.Gravity.NO_GRAVITY
            var texts = LinearLayout(this)
            texts.orientation = LinearLayout.VERTICAL
            texts.gravity = android.view.Gravity.NO_GRAVITY
            var image = ImageView(this) //Exercise IMG
            image.setOnClickListener { showLink(i.youtubeLink) }
            val params2 = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            params2.setMargins(0,0,20,0)
            image.setImageResource(R.drawable.yt)
            val name = TextView(this)
            name.text = i.ExerciceName
            name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
            texts.addView(name)
            if (i.ExerciceName != "Dia Libre") {
                val reps = TextView(this)
                reps.text = "Reps: " + Controlador.usuario.findRutinaByName(title).reps.toString()
                reps.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
                texts.addView(reps)
                if (Controlador.usuario.findRutinaByName(title).obj != "Cardio") {
                    val sets = TextView(this)
                    sets.text =
                        "Sets: " + Controlador.usuario.findRutinaByName(title).sets.toString()
                    sets.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
                    texts.addView(sets)
                    val rest = TextView(this)
                    rest.text = "Rest (Secs): " + Controlador.usuario.findRutinaByName(title).rest.toString()
                    rest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
                    texts.addView(rest)
                }
            }


            parent.addView(image, params2)
            parent.addView(texts)
            layout.addView(parent, params)
        }
        var tomorrowText: TextView = TextView(this)
        tomorrowText.text = "Come back tomorrow to see what's your next workout!"
        var parametro = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        parametro.setMargins(0,30,0,0)
        tomorrowText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        tomorrowText.gravity = android.view.Gravity.CENTER
        layout.addView(tomorrowText, parametro)
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
