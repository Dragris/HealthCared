package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.healthcared.R
import kotlinx.android.synthetic.main.activity_singup2.*

class Singup2 : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val gender = arrayOf("-- Select Gender --", "Male", "Female")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup2)

        // Gender Spinner
        spinner_gender!!.setOnItemSelectedListener(this)
        val arrayAdapter = ArrayAdapter<String>(this,
            R.layout.spinner_item, gender)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinner_gender!!.setAdapter(arrayAdapter)


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //THINGS
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //THINGS
    }

    fun home(view: View){
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
    }

    fun goBack(view: View){
        finish()
    }
}
