package com.example.healthcared.UI

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.*
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
        text2.text = "This diets are just recomendations"
        textView.gravity = android.view.Gravity.CENTER
        text2.gravity = android.view.Gravity.CENTER
        layout.addView(textView)
        layout.addView(text2)
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
            var image = ImageView(this) //Exercise IMG
            image.setImageResource(R.drawable.common_google_signin_btn_icon_light)
            val name = TextView(this)
            if (i == 1){
                name.text = "Breakfast"
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                texts.addView(name)
            } else if (i == 2) {
                name.text = "Lucnh"
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                texts.addView(name)
            } else {
                name.text = "Dinner"
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                texts.addView(name)
            }
            for (j in dietas){
                for (z in j){
                    if (z.time == i && z.dia == dia+1) {
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
            parent.addView(image)
            parent.addView(texts)
            layout.addView(parent, params)
        }


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
}

