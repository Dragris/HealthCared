package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.marginBottom
import com.example.healthcared.Controlador
import com.example.healthcared.R

class WorkoutHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_home)
        initButtons()
        val layout = findViewById<LinearLayout>(R.id.layout)
        /*for (i in 0..(Controlador.instance.usuario.rutinas.size-1)){
            val button = Button(this)
            button.width = LinearLayout.LayoutParams.MATCH_PARENT
            button.height = LinearLayout.LayoutParams.WRAP_CONTENT
            button.text = Controlador.instance.usuario.rutinas[i].rutinaName
            button.textSize = R.dimen.normal_text as Float
        }*/
        //Crear botones asi:
        val button = Button(this)
        button.width = LinearLayout.LayoutParams.MATCH_PARENT
        button.height = LinearLayout.LayoutParams.WRAP_CONTENT
        button.text = "HELLO"


        layout.addView(button)
/*
        <Button
        android:id="@+id/example_plan"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:background="@color/transparency_white"
        android:text="@string/plan_name_1"
        android:textSize="@dimen/normal_text"
        android:textStyle="bold"
        android:layout_marginBottom="@dimen/top_margin"/>
*/
    }

    fun goBack(view: View) {
        finish()
    }

    fun planPreview(view: View, string: String){
        val intent = Intent(this, PlanPreview::class.java)
        intent.putExtra("title", string)
        startActivity(intent)
    }

    fun settings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun initButtons(){
        findViewById<Button>(R.id.example_plan).setOnClickListener {
            planPreview(findViewById(R.id.example_plan), findViewById<Button>(
                R.id.example_plan
            ).text as String)
        }
        findViewById<Button>(R.id.example_plan2).setOnClickListener {
            planPreview(findViewById(R.id.example_plan), findViewById<Button>(
                R.id.example_plan2
            ).text as String)
        }
    }

    fun createPlan(view: View){
        val intent = Intent(this, CreatePlan::class.java)
        startActivity(intent)
    }
}
