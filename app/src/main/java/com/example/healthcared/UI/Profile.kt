package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
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
            Controlador.usuario.height = height as Int
        }
    }
    fun updateW(view: View){
        var weight = findViewById<EditText>(R.id.fullname).text.toString()
        if (weight != ""){
            Controlador.usuario.height = weight as Int
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
        }
    }

    fun updateP(){
        // Pa el futuro
    }

    fun changeProfileImage(view: View){
        //Change profile picture.
    }
}
