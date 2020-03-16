package com.example.healthcared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun goBack(view: View){
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }
    fun changeUsername(view: View){
        //Change Username.
    }
    fun changePassword(view: View){
        //Change Password.
    }

    fun goSupport(view: View){
        //Support.
    }
    fun goFaq(view: View){
        //Faq.
    }
    fun signOut(view: View){
        //Sign Out
    }
}
