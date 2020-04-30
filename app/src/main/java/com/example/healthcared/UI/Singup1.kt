package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.example.healthcared.Modelo.Usuario
import com.example.healthcared.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup1.*

class Singup1: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup1)
    }

    public override fun onStart() {
        super.onStart()
    }

    fun signup2(view: View) {
        val checkBox: CheckBox = findViewById(R.id.checkBox)
        if (!checkBox.isChecked) {
            Toast.makeText(
                baseContext, "You must agree to terms & conditions of usage.",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val email: String = findViewById<EditText>(R.id.email).text.toString()
            val name: String = findViewById<EditText>(R.id.fullname).text.toString()
            val username: String = findViewById<EditText>(R.id.user).text.toString()
            val password: String = findViewById<EditText>(R.id.password).text.toString()
            val repeat: String = findViewById<EditText>(R.id.password_repeat).text.toString()
            if (email == ("") || name == ("") || username == ("") || password == ("") || repeat == ("")) {
                Toast.makeText(baseContext, "There are some fields empty", Toast.LENGTH_SHORT)
                    .show()
            } else if(password.length < 6){
                Toast.makeText(baseContext, "Password must have 6 or more characters", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (password != repeat) {
                    Toast.makeText(baseContext, "The passwords don't match", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val intent = Intent(this, Singup2::class.java)
                    intent.putExtra("email", email)
                    intent.putExtra("name", name)
                    intent.putExtra("username", username)
                    intent.putExtra("password", password)
                    startActivity(intent)
                }
            }


        }
    }

    fun goBack(view: View) {
        finish()
    }
}


