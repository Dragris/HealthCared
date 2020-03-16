package com.example.healthcared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_create_plan.*

class CreatePlan : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var days = arrayOf(1, 2, 3, 4, 5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_plan)
        spinner_days!!.setOnItemSelectedListener(this)
        val arrayAdapter =  ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_days!!.setAdapter(arrayAdapter)
    }

    fun goBack(view: View){
        val intent = Intent(this, WorkoutHome::class.java)
        startActivity(intent)
    }

    fun goProfile(view: View){
        //val intent = Intent(this, profile::class.java)
        //startActivity(intent)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //THINGS
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //THINGS
    }

    fun addPlan(view: View){
        //THINGS
    }
}
