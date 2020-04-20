package com.example.healthcared.UI

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.healthcared.*
import com.example.healthcared.Modelo.Rutina
import com.example.healthcared.Modelo.Usuario
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_inicio.barChart
import java.util.*

class Inicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val controlador: Controlador
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f,200f))
        entries.add(BarEntry(2f,600f))
        entries.add(BarEntry(4f,988f))
        entries.add(BarEntry(6f,9084f))
        entries.add(BarEntry(8f,15000f))
        entries.add(BarEntry(10f,759f))
        entries.add(BarEntry(12f, 100f))

        val barDataSet = BarDataSet(entries, "Pasos")

        val labels = ArrayList<String>()
        labels.add("Hace 1 semana")
        labels.add("Hace 6 días")
        labels.add("Hace 5 días")
        labels.add("Hace 4 días")
        labels.add("Hace 3 días")
        labels.add("Hace anteayer")
        labels.add("Hace ayer")
        labels.add("Hoy")

        val data = BarData(barDataSet)
        barChart.data = data

        barDataSet.color = resources.getColor(R.color.colorAccent)

        barChart.setScaleEnabled(false);
        barChart.animateX(2500)
        barChart.animateY(5000)
    }

    /**
     *Función para que la flecha atrás no lleve al registro/log in
     */
    override fun onBackPressed() {}

    fun tracker (view: View){
        trackerPermissionCheck()
    }

    fun workouts (view: View){
        val intent = Intent(this, WorkoutHome::class.java)
        startActivity(intent)
    }

    fun profile(view: View){
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }
    fun diets(view: View){
        val intent = Intent(this, DietHome::class.java)
        startActivity(intent)
    }

    fun settings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun pause(view: View) {
        // FUncion usada pare crear rutinas (test) -> var rutina = Rutina("GG", 2, "Toy Gordo", 1)
        var rutina = Rutina("GG", 1, "Toy Gordo", 4)
        Log.v("sgfd", Controlador.usuario.dietas.toString())
    }





    companion object {
        private const val FIRST_LOCATION_PERMISSION_REQUEST_CODE = 1138
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray){
        when (requestCode){
            FIRST_LOCATION_PERMISSION_REQUEST_CODE ->{
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    val intent = Intent(this, Tracker::class.java)
                    startActivity(intent)
                } else {
                    //No se concede el permiso, no se hace nada
                }
            }
        }
    }

    fun trackerPermissionCheck(){
        //Check location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), FIRST_LOCATION_PERMISSION_REQUEST_CODE )
            return
        }
        val intent = Intent(this, Tracker::class.java)
        startActivity(intent)
    }
}
