package com.example.healthcared.UI

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.BoringLayout
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.healthcared.Controlador
import com.example.healthcared.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception


class LogIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var username : EditText
    private lateinit var password: EditText
    private  var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        /**
         * Comes from successful register
         */
        try {
            var boolean: String = intent.getStringExtra("register") as String
            if (boolean == "yes") {
                Toast.makeText(
                    baseContext, "Successfully registered",
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception){
            //Do nothing
        }


        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        auth.signOut()

        username = findViewById(R.id.username)
        password = findViewById(R.id.Upassword)
        findViewById<Button>(R.id.login_btn).visibility = View.VISIBLE
    }

    //btn Login
    fun login(view: View) {
        findViewById<Button>(R.id.login_btn).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.sign_up).visibility = View.INVISIBLE
        findViewById<ProgressBar>(R.id.misTetas).visibility = View.VISIBLE
        //controlar la entrada
            if(username.text.toString().isEmpty()){
                username.error = "Please enter a username"
                username.requestFocus()
                findViewById<Button>(R.id.login_btn).visibility = View.VISIBLE
                findViewById<TextView>(R.id.sign_up).visibility = View.VISIBLE
                findViewById<ProgressBar>(R.id.misTetas).visibility = View.INVISIBLE
                return
    }   //controlar la entrada
            if(password.text.toString().isEmpty()){
                password.error = "Please enter a password"
                password.requestFocus()
                findViewById<Button>(R.id.login_btn).visibility = View.VISIBLE
                findViewById<TextView>(R.id.sign_up).visibility = View.VISIBLE
                findViewById<ProgressBar>(R.id.misTetas).visibility = View.INVISIBLE
                return
            }
        //SignIn to Firebase
        Log.d("username",username.text.toString())
        Log.d("password",password.text.toString())
        auth.signInWithEmailAndPassword(username.text.toString() , password.text.toString())
            .addOnSuccessListener {
                currentUser = auth.currentUser
                Controlador.cargarDatos()
                val handler = Handler()
                handler.postDelayed(Runnable {
                    val intent = Intent(this, Inicio::class.java)
                    startActivity(intent)
                }, 3000)
            }
            .addOnFailureListener {
                val string = it.toString()
                var index = string.indexOf(':')
                val domain: String? = if (index == -1) null else string.substring(index + 1)
                Toast.makeText(baseContext, domain,Toast.LENGTH_SHORT).show()
                updateUI(null)
                findViewById<Button>(R.id.login_btn).visibility = View.VISIBLE
                findViewById<TextView>(R.id.sign_up).visibility = View.VISIBLE
                findViewById<ProgressBar>(R.id.misTetas).visibility = View.INVISIBLE
            }
    }

    fun signup1(view: View) {
        val intent = Intent(this, Singup1::class.java)
        startActivity(intent)
    }


    public override fun onStart(){
    super.onStart()
    val currentUser:FirebaseUser? =auth.currentUser
    }

    private fun updateUI(currentUser : FirebaseUser?){
        if(currentUser !=null) {
            Toast.makeText(baseContext,"Please verify your email address",Toast.LENGTH_SHORT).show()
        }
    }
}

