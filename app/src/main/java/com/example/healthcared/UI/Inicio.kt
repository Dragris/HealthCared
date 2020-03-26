package com.example.healthcared.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcared.*
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_inicio.barChart
import java.util.ArrayList

class Inicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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


    override fun onBackPressed() {}

    fun tracker (view: View){
        val intent = Intent(this, Tracker::class.java)
        startActivity(intent)
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
}
