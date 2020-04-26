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
import com.example.healthcared.Modelo.Usuario
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

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        dbReference=database.reference.child("User")
        var checkbox = findViewById<CheckBox>(R.id.checkBox)

        checkbox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {
            buttonView: CompoundButton?, isChecked: Boolean ->

        })

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
        //TODO crear controlador
    }

    fun signup2(view: View) {

        if(checkSignUp()){

            auth.createUserWithEmailAndPassword(txtEmail.text.toString().trim(), txtPassword.text.toString().trim())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user:FirebaseUser? =auth.currentUser

                        //TODO crear el nuevo user en controlador
                        
                        /*intent.putExtra("fullname",txtFullname.text.toString())
                        intent.putExtra("email",txtEmail.text.toString())
                        intent.putExtra("username",txtUsername.text.toString())
                        intent.putExtra("password",txtPassword.text.toString())*/

                        val intent = Intent(this, Singup2::class.java)
                        startActivity(intent)

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


    fun checkSignUp():Boolean{

        if(checkBox.isChecked){
            if (authPassword(txtPassword.toString(),  txtRepeatPassword.toString())){
                return true
            }else {
                Toast.makeText(applicationContext, "Both Passwords Must Match!", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(applicationContext, "You have to accept the terms first!", Toast.LENGTH_LONG).show()
        }

       return false
    }

    fun authPassword(pass1: String, pass2: String): Boolean{

        if(pass1.equals(pass2)){
            return true
        }else{
            return false
            //TODO mensaje para correjir la contraseña
        }
    }




    fun goBack(view: View){
        finish()
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null){

        }

    }

}




