package com.example.healthcared.UI

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.healthcared.Controlador
import com.example.healthcared.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LogIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var username : EditText
    private lateinit var password: EditText


    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        username = findViewById(R.id.username)
        password = findViewById(R.id.Upassword)

    }
//btn Login
    fun login(view: View) {

    var username1: String = username.text.toString()

    var password1: String = password.text.toString()

    //controlar la entrada
        if(username1.isEmpty()){
            username.error = "Please enter a username"
            username.requestFocus()
            return
}   //controlar la entrada
        if(password1.isEmpty()){
            password.error = "Please enter a password"
            password.requestFocus()
            return
        }
    //SignIn to Firebase
    Log.d("username",username1)
    Log.d("password",password1)

    auth?.signInWithEmailAndPassword(username1 , password1)
        ?.addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                Log.d("Successful","LOG IN")

                //update user
               /* val editor = prefs!!.edit()
                editor.putString("USER_ID" , username1)
                editor.apply()


                //val user = auth?.currentUser*/

                val intent = Intent(this, Inicio::class.java)
                startActivity(intent)

            } else {
                Log.d("Un Successful","NO LOGin")

                updateUI(null)
                // ...
            }

            // ...
        }

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
    /*val currentUser:FirebaseUser? =auth.currentUser
    updateUI(currentUser)*/
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
            Toast.makeText(baseContext,"login failed",Toast.LENGTH_SHORT).show()
        }
    }
}

