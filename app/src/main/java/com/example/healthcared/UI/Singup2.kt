package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.healthcared.Controlador
import com.example.healthcared.Modelo.Usuario
import com.example.healthcared.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import kotlinx.android.synthetic.main.activity_signup1.*
import kotlinx.android.synthetic.main.activity_singup2.*
import java.util.*

class Singup2 : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var controlador: Controlador = Controlador
    private lateinit var auth: FirebaseAuth
    lateinit var db : FirebaseFirestore
    lateinit var usersList:MutableList<Usuario>
    val gender = arrayOf("-- Select Gender --", "Male", "Female")
    val userData = mutableListOf<String>("", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup2)
        usersList = mutableListOf()
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        //crear un variable intenet para guardar los dados que vienen del singup_1
        val intent = intent
        userData[0] = intent.getStringExtra("name")
        userData[1] = intent.getStringExtra("email")
        userData[2] = intent.getStringExtra("password")


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
        findViewById<Button>(R.id.login_btn).visibility = View.INVISIBLE
        findViewById<ProgressBar>(R.id.misTetas).visibility = View.VISIBLE
        val name = userData[0]
        val email = userData[1]
        val password = userData[2]

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
            val newUser = Usuario(name, email, password, vegan, veget)
            newUser.height = height.toLong()
            newUser.weight = weight.toLong()
            newUser.targetSteps = date.toLong()
            newUser.gender = spinner

            Log.v("SIGNUP MAIL", email)
            Log.v("SIGNUP PASS", password)
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        var userID = user?.uid
                        var documentReference = db.collection("Users").document(userID!!)
                        var userData = mapOf("userObject" to newUser)
                        documentReference.set(userData)

                        val intent = Intent(this, Inicio::class.java)
                        Controlador.usuario = newUser
                        startActivity(intent)
                    } else {
                        Log.w("createUserWithEmail", task.exception)
                        val string = task.exception.toString()
                        val index = string.indexOf(':')
                        val domain: String? = if (index == -1) null else string.substring(index + 1)
                        Toast.makeText(
                            baseContext, domain,
                            Toast.LENGTH_LONG
                        ).show()
                        findViewById<Button>(R.id.login_btn).visibility = View.VISIBLE
                        findViewById<ProgressBar>(R.id.misTetas).visibility = View.INVISIBLE

                    }
                }
                .addOnFailureListener { e -> Toast.makeText(baseContext, e.toString(), Toast.LENGTH_LONG) }

        }
    }

    fun goBack(view: View){
        finish()
    }
}
