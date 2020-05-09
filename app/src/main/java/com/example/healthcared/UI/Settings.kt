package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcared.Controlador
import com.example.healthcared.UI.Inicio
import com.example.healthcared.R
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    override fun onBackPressed() {
        startActivity(Intent(this,Inicio::class.java))
        finish()
    }
    fun goBack(view: View){
        startActivity(Intent(this,Inicio::class.java))
        finish()
    }
    fun changeUsername(view: View){
        //Change Username.
    }
    fun changePassword(view: View){
        //Change Password.
    }
    fun changeDiet(view: View){
        //Change diet
    }

    fun goSupport(view: View){
        //Support.
    }
    fun goFaq(view: View){
        //Faq.
    }
    fun signOut(view: View){
        Controlador.guardarDatos()
        FirebaseAuth.getInstance().signOut()
        var login = Intent(this, LogIn::class.java)
        startActivity(login)
        finish()
    }
}
