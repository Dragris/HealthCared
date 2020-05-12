package com.example.healthcared.UI

import android.content.Intent
import android.net.Uri
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

    fun goSupport(view: View){
        var lista = arrayOf("support@healthcared.com")
        var intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, lista)
        intent.type = "plain/text"
        startActivity(Intent.createChooser(intent, "Choose an application"))
    }
    fun goFaq(view: View){
        var link = "https://ubarcelona-my.sharepoint.com/:w:/g/personal/dlopezad7_alumnes_ub_edu/EUKfLg0-0KBNjT8hssqPusQBX9W2YXyn2BieUyq52TulKQ?e=Efzkxu"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)

    }
    fun signOut(view: View){
        Controlador.guardarDatos()
        FirebaseAuth.getInstance().signOut()
        var login = Intent(this, LogIn::class.java)
        startActivity(login)
        finish()
    }
}
