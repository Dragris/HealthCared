package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.healthcared.Controlador
import com.example.healthcared.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LogIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var username : EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        username = findViewById(R.id.username)
        password = findViewById(R.id.Upassword)

    }
//btn Login
    fun login(view: View) {
    //controlar la entrada
        if(username.text.toString().isEmpty()){
            username.error = "Please enter a username"
            username.requestFocus()
            return
}   //controlar la entrada
        if(password.text.toString().isEmpty()){
            password.error = "Please enter a password"
            password.requestFocus()
            return
        }
    //SignIn to Firebase
        auth.signInWithEmailAndPassword(username.text.toString() , password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
//update user
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                    // ...
                }

                // ...
            }
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
    }

    fun signup1(view: View) {

        val intent = Intent(this, Singup1::class.java)
        startActivity(intent)
    }

    /**
     * este metodo mira si ya hay un usuario conectado, para saltar directamente al layot Inico
     * esta petando el aplicacion con este metodo
     */
    public override fun onStart(){
    super.onStart()
    val currentUser:FirebaseUser? =auth.currentUser
    updateUI(currentUser)
}

    private fun updateUI(currentUser : FirebaseUser?){
        if(currentUser !=null) {
            //verificar el email registrado
            /**
             * if (currentUser.isEmailVerified) {
            startActivity(Intent(this, Inicio::class.java))
            }
             */

                Toast.makeText(baseContext,"Please verify your email address",Toast.LENGTH_SHORT).show()

        }
        else{
            Toast.makeText(baseContext,"logain failed",Toast.LENGTH_SHORT).show()
        }
    }
}
