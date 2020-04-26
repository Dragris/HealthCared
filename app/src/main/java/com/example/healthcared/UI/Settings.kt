package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcared.R
import com.google.firebase.auth.FirebaseAuth

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun goBack(view: View){
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
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,LogIn::class.java))
    }
}
