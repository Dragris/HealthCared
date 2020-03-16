package com.example.healthcared

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Singup2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_singup2)

        val btnfemale = findViewById<Button>(R.id.femalebtn)
        val btn = findViewById<Button>(R.id.malebtn)
        val ages = arrayOf("18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40")
        val spinner = findViewById<Spinner>(R.id.spinnerx)


        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ages)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) { // Con este codigo  me pita la app cuando entre al Layout Singup2
                //result_age.text = ages.get(position)
            }



        }
        /*
        Cambair los colores del los botones MAle y Female
         */
     btn.setOnClickListener(View.OnClickListener {
         btnfemale.setBackgroundColor(Color.WHITE)
         btn.setBackgroundColor(Color.GRAY)

     })
/*
        Cambair los colores del los botones MAle y Female
         */
        btnfemale.setOnClickListener(View.OnClickListener {
            btn.setBackgroundColor(Color.WHITE)
            btnfemale.setBackgroundColor(Color.GRAY)


        })
    }





}
