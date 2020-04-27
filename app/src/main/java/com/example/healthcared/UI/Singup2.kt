package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.healthcared.Controlador
import com.example.healthcared.Modelo.Usuario
import com.example.healthcared.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import kotlinx.android.synthetic.main.activity_signup1.*
import kotlinx.android.synthetic.main.activity_singup2.*
import java.util.*

class Singup2 : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var controlador: Controlador = Controlador
    private lateinit var auth: FirebaseAuth
    lateinit var db : DatabaseReference
    lateinit var usersList:MutableList<Usuario>
    val gender = arrayOf("-- Select Gender --", "Male", "Female")
    val userData = mutableListOf<String>("", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup2)
        usersList = mutableListOf()
        //crear un variable intenet para guardar los dados que vienen del singup_1
        val intent = intent
        userData[0] = intent.getStringExtra("name")
        userData[1] = intent.getStringExtra("email")
        userData[2] = intent.getStringExtra("username")
        userData[3] = intent.getStringExtra("password")


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

    fun Singup (view: View) {
        val name = userData[0]
        val email = userData[1]
        val username = userData[2]
        val password = userData[3]

        val spinner: String = findViewById<Spinner>(R.id.spinner_gender).selectedItem.toString()
        val date: String = findViewById<EditText>(R.id.date).text.toString()
        val height: String = findViewById<EditText>(R.id.height).text.toString()
        val weight: String = findViewById<EditText>(R.id.weight).text.toString()

        val groupDieta = findViewById<RadioGroup>(R.id.dietas)
        val dietaID = groupDieta.checkedRadioButtonId

        if (date == ("") || height == ("") || height == ("") || weight == ("") || spinner == ("-- Select Gender --") || dietaID == -1) {
            Toast.makeText(baseContext, "There are some fields empty", Toast.LENGTH_SHORT)
                .show()
        } else {
            val radioButton: RadioButton = findViewById(dietaID)
            val indexDietas = groupDieta.indexOfChild(radioButton)
            var vegan: Boolean
            var veget: Boolean
            if (indexDietas == 0) {
                vegan = false
                veget = false
            } else if (indexDietas == 1) {
                vegan = false
                veget = true
            } else {
                vegan = true
                veget = true
            }
            val newUser = Usuario(name, email, username, password, vegan, veget)
            newUser.height = height.toInt()
            newUser.wieght = weight.toInt()
            newUser.bDate = date
            newUser.gender = spinner

            db = FirebaseDatabase.getInstance().getReference("Users")
            db.addValueEventListener(object : ValueEventListener {
                //onCancelled la dejamos vacia
                override fun onCancelled(p0: DatabaseError) {
                    //TODO
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0!!.exists())
                        usersList.clear()
                    for (e in p0.children) {
                        val user = e.getValue(Usuario::class.java)
                        usersList.add(newUser!!)
                    }
                }
            })
            val intent = Intent(this, Inicio::class.java)
            Controlador.usuario = newUser
            startActivity(intent)
        }
    }

    fun goBack(view: View){
        finish()
    }
}
