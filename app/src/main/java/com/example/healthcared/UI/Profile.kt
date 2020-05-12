package com.example.healthcared.UI

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.healthcared.Controlador
import com.example.healthcared.R

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    fun goBack(view: View){
        finish()
    }

    fun settings (view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun updateH(view: View){
        var height = findViewById<EditText>(R.id.email).text.toString()
        if (height != ""){
            Controlador.usuario.height = height.toLong()
            Controlador.updateUser()
            Toast.makeText(this, "Changes will be applied in next restart", Toast.LENGTH_LONG).show()
        }
    }
    fun updateW(view: View){
        var weight = findViewById<EditText>(R.id.fullname).text.toString()
        if (weight != ""){
            Controlador.usuario.weight = weight.toLong()
            Controlador.updateUser()
            Toast.makeText(this, "Changes will be applied in next restart", Toast.LENGTH_LONG).show()
        }
    }

    fun updateD(view: View){
        val groupDieta = findViewById<RadioGroup>(R.id.dietas)
        val dietaID = groupDieta.checkedRadioButtonId
        if (dietaID != -1){
            Controlador.usuario.dietas = mutableListOf()
            val radioButton: RadioButton = findViewById(dietaID)
            val indexDietas = groupDieta.indexOfChild(radioButton)
            if (indexDietas == 0) {
                Controlador.usuario.dietas.add(Controlador.normal)
                Controlador.usuario.dietas.add(Controlador.veget)
            } else if (indexDietas == 1) {
                Controlador.usuario.dietas.add(Controlador.veget)
                Controlador.usuario.dietas.add(Controlador.vegan)
            } else {
                Controlador.usuario.dietas.add(Controlador.vegan)
            }
            Controlador.updateUser()
            Toast.makeText(this, "Your diet has changed!", Toast.LENGTH_LONG).show()
        }
    }

    fun updateP(view: View){
        var steps = findViewById<EditText>(R.id.maxSteps).text.toString()
        if(steps != ""){
            Controlador.updateUserMaxSteps(steps.toLong())
            Controlador.guardarDatos()
            Toast.makeText(this, "Changes will be applied in next restart", Toast.LENGTH_LONG).show()
        }
    }

    fun changeProfileImage(view: View){
        //Change profile picture.
    }
}
