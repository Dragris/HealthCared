package com.example.healthcared.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.lifecycle.whenCreated
import com.example.healthcared.Controlador
import com.example.healthcared.Modelo.Rutina
import com.example.healthcared.R
import kotlinx.android.synthetic.main.activity_create_plan.*
import com.example.healthcared.ProgressBar
import java.util.*
import java.util.concurrent.TimeUnit

class CreatePlan : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var days = arrayOf(1, 2, 3, 4, 5)
    var objectiveTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_plan)
        spinner_days!!.setOnItemSelectedListener(this)
        val arrayAdapter =  ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_days!!.setAdapter(arrayAdapter)

    }

    fun goBack(view: View){
        val intent = Intent(this, WorkoutHome::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() { startActivity(Intent(this, WorkoutHome::class.java)); finish() }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //THINGS
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //THINGS
    }

    fun addPlan(view: View) {
        var name: String
        val group = findViewById<RadioGroup>(R.id.group)
        val objID = group.checkedRadioButtonId
        if (objID == -1) {
            Toast.makeText(baseContext, "Please select an objective", Toast.LENGTH_SHORT)
        } else {
            val radioButton: RadioButton = findViewById(objID)
            when (group.indexOfChild(radioButton)) {
                0 -> objectiveTitle = "Toy Gordo"
                1 -> objectiveTitle = "Cardio"
                2 -> objectiveTitle = "Fuelsa"
                3 -> objectiveTitle = "Mamadisimo"
            }

            if (findViewById<EditText>(R.id.plan_name_creator).text.toString() == "") {
                name = "Plan " + Controlador.usuario.cont.toString()
                Controlador.usuario.cont += 1
            } else {
                name = findViewById<EditText>(R.id.plan_name_creator).text.toString()
            }

            if (planNameExists(name)) {
                val toast = Toast.makeText(
                    applicationContext,
                    "You already have a routine with that name",
                    Toast.LENGTH_LONG
                )
                toast.show()
            } else {
                var auxdaysxweek: Int = findViewById<Spinner>(R.id.spinner_days).selectedItem as Int
                var daysxweek: Long = auxdaysxweek.toLong()
                var auxskill: Int = findViewById<SeekBar>(R.id.seekBar).progress + 1 as Int
                var skill: Long = auxskill.toLong()
                var rutina = Rutina(name, skill, objectiveTitle, daysxweek)
                Controlador.usuario.rutinas.add(rutina)
                val intent = Intent(this, ProgressBar::class.java)
                intent.putExtra("title", "Creating Workout Routine")
                startActivity(intent)
                Controlador.updateUser()
                finish()
            }
        }
    }

    fun planNameExists(name: String): Boolean { //True -> Si existe, False -> No existe
        for (i in Controlador.usuario.rutinas){
            if (i.rutinaName == name && i.difficulty != 0L){
                return true
            }
        }
        return false
    }
}