package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.example.healthcared.Modelo.Usuario
import com.example.healthcared.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import kotlinx.android.synthetic.main.activity_singup2.*

class Singup2 : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var txtdate : EditText
    private lateinit var txtheight: EditText
    private lateinit var txtweight: EditText
    private lateinit var spinner_Gender: Spinner
    private lateinit var  fullname : String
    private lateinit var  email : String
    private lateinit var  username : String
    private lateinit var  password : String
    lateinit var db : DatabaseReference
lateinit var usersList:MutableList<Usuario>


    val gender = arrayOf("-- Select Gender --", "Male", "Female")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup2)
        usersList = mutableListOf()
        //crear un variable intenet para guardar los dados que vienen del singup_1
        val intent = intent
        fullname = intent.getStringExtra("fullname")
        email = intent.getStringExtra("email")
        username = intent.getStringExtra("username")
        password = intent.getStringExtra("password")
        //
        txtdate = findViewById(R.id.date)
        txtheight = findViewById(R.id.height)
        txtweight = findViewById(R.id.weight)
        spinner_Gender = findViewById(R.id.spinner_gender)
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

    fun Singup (view: View){

        //crear el nuevo usuiaro
        val new_user = Usuario(fullname,email,username,password,false,false) //TODO arreglar vegan/veggie
        new_user.height = Integer.parseInt(txtheight.text.toString().trim())
        new_user.wieght = Integer.parseInt(txtweight.text.toString().trim())
        new_user.age = Integer.parseInt(txtdate.text.toString().trim())
        new_user.gender = spinner_Gender.text.toString().trim()
        //Guardad dados en firebase
        db = FirebaseDatabase.getInstance().getReference("Users")
        db.addValueEventListener(object :ValueEventListener{
            //onCancelled la dejamos vacia
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists())
                    usersList.clear()
                    for (e in p0.children){
                        val user = e.getValue(Usuario::class.java)
                        usersList.add(new_user!!)

                    }

            }

        })
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
    }

    fun goBack(view: View){
        finish()
    }
}
