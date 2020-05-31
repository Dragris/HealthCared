package com.example.healthcared.UI

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.view.marginTop
import com.example.healthcared.Controlador
import com.example.healthcared.Modelo.Comida
import com.example.healthcared.R
import java.util.*

class DietHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_home)
        val layout = findViewById<LinearLayout>(R.id.layout)
        val dia = getDayofWeek()
        val dietas = mutableListOf<MutableList<Comida>>()
        for (i in Controlador.usuario.dietas){
            dietas.add(i.comidas)
        }
        val textView = TextView(this)
        val text2 = TextView(this)
        textView.text = "REMEMBER!"
        text2.text = "These diets are just recommendations"
        textView.gravity = android.view.Gravity.CENTER
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        text2.gravity = android.view.Gravity.CENTER
        text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        val parametros = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        parametros.setMargins(0,0,0,50)
        layout.addView(textView, parametros)
        layout.addView(text2, parametros)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(100,0,0,30)
        for (i in 1..3){
            var parent = LinearLayout(this)
            parent.orientation = LinearLayout.HORIZONTAL
            parent.gravity = android.view.Gravity.NO_GRAVITY
            var texts = LinearLayout(this)
            texts.orientation = LinearLayout.VERTICAL
            texts.gravity = android.view.Gravity.NO_GRAVITY
            val name = TextView(this)
            if (i == 1){
                name.text = "Breakfast"
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                texts.addView(name)
            } else if (i == 2) {
                name.text = "Lunch"
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                texts.addView(name)
            } else {
                name.text = "Dinner"
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                texts.addView(name)
            }
            for (j in dietas){
                for (z in j){
                    var auxi: Long = i.toLong()
                    var auxDia: Long = (dia + 1).toLong()
                    if (z.time == auxi && z.dia == auxDia) {
                        val food = TextView(this)
                        food.text = z.foodName
                        food.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
                        food.setOnClickListener {
                            if (z.recipeLink == "-") {
                                val toast = Toast.makeText(
                                    applicationContext,
                                    "This dish has no recipe",
                                    Toast.LENGTH_LONG
                                )
                                toast.show()
                            } else {
                                showLink(z.recipeLink)
                            }
                        }
                        texts.addView(food)
                    }
                }
            }
            parent.addView(texts)
            layout.addView(parent, params)
        }
        var tomorrowText: TextView = TextView(this)
        tomorrowText.text = "Come back tomorrow to see new dishes!"
        var tap = TextView(this)
        tap.text = "Tap the name of the dish to get the recipie!"
        var parametro = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        parametro.setMargins(0,30,0,0)
        tomorrowText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        tomorrowText.gravity = android.view.Gravity.CENTER
        tap.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        tap.gravity = android.view.Gravity.CENTER
        layout.addView(tomorrowText, parametro)
        layout.addView(tap, parametro)

    }

    fun goBack(view: View) {
        finish()
    }

    fun settings(view: View){
        val intent = Intent(this, Settings::class.java)
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

    fun tracker(view: View) {
        trackerPermissionCheck()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1138
    }

    fun trackerPermissionCheck() {
        //Check location permissions
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                DietHome.LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        //If already given
        val intent = Intent(this, Tracker::class.java)
        startActivity(intent)
        finish()
    }

    fun profile(view: View) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
        finish()
    }

    fun workouts(view: View) {
        val intent = Intent(this, WorkoutHome::class.java)
        startActivity(intent)
        finish()
    }

    fun home(view: View) {
        finish()
    }
}

