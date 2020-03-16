package com.example.healthcared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup1.*

class Singup1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup1)
        var checkbox = findViewById<CheckBox>(R.id.checkBox)

        checkbox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {
            buttonView: CompoundButton?, isChecked: Boolean ->

        })
    }


    fun actionterms(view: View) {
        if(checkBox.isChecked){
            val intent = Intent(this,Singup2::class.java)
            startActivity(intent)

        }
        else{
            Toast.makeText(applicationContext, "You have to accpete the terms first!", Toast.LENGTH_LONG).show()
        }

    }



}




