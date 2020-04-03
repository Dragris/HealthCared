package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast
import com.example.healthcared.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup1.*

class Singup1 : AppCompatActivity() {
    private lateinit var txtEmail : EditText
    private lateinit var txtFullname: EditText
    private lateinit var txtUsername: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtRepeatPassword: EditText
    private lateinit var dbReference: DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth
    // VARIABLES DE LOS DADOS



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup1)
        txtEmail = findViewById(R.id.email)
        txtFullname = findViewById(R.id.fullname)
        txtUsername = findViewById(R.id.user)
        txtPassword = findViewById(R.id.password)
        txtRepeatPassword= findViewById(R.id.password_repeat)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference=database.reference.child("User")
        var checkbox = findViewById<CheckBox>(R.id.checkBox)

        checkbox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {
            buttonView: CompoundButton?, isChecked: Boolean ->

        })

    }




    fun signup2(view: View) {
        if(checkBox.isChecked){
            auth.createUserWithEmailAndPassword(txtEmail.text.toString().trim(), txtPassword.text.toString().trim())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user:FirebaseUser? =auth.currentUser

                        startActivity(Intent(this,Inicio::class.java))
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }

                    // ...
                }

            val intent = Intent(this, Singup2::class.java)
            startActivity(intent)
        }
        else{
            Toast.makeText(applicationContext, "You have to accept the terms first!", Toast.LENGTH_LONG).show()
        }
    }

    fun goBack(view: View){
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)
    }



}




