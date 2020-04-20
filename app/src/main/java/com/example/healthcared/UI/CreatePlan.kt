package com.example.healthcared.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.healthcared.Controlador
import com.example.healthcared.Modelo.Rutina
import com.example.healthcared.R
import kotlinx.android.synthetic.main.activity_create_plan.*
import java.util.*

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
        finish()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //THINGS
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //THINGS
    }

    fun changeOb(view: View){
        //TODO que cambien de color los botones
        var button = view as Button
        var name: String = button.text.toString()
        if (name == "Lose Weight")  { objectiveTitle = "Toy Gordo" }
        else if (name == "Cardio/Tonify") { objectiveTitle = "Cardio" }
        else if (name == "Gain Strenght") { objectiveTitle = "Fuelsa" }
        else { objectiveTitle = "Toy Mamadisimo" }
    }

    fun addPlan(view: View){
        var name: String
        if (findViewById<EditText>(R.id.plan_name_creator).text.toString() == null){
            name = "Plan"
        } else {
            name = findViewById<EditText>(R.id.plan_name_creator).text.toString()
        }
        Log.v("ef", name)
        var daysxweek: Int = findViewById<Spinner>(R.id.spinner_days).selectedItem as Int
        var skill: Int = findViewById<SeekBar>(R.id.seekBar).progress + 1
        var rutina = Rutina(name, skill, objectiveTitle, daysxweek)
        Controlador.usuario.rutinas.add(rutina)
        finish()
        //TODO mostrar el plan y añadir botones
    }
}
