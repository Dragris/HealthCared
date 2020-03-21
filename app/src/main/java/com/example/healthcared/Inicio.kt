package com.example.healthcared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        entries.add(BarEntry(1f,0f))
        entries.add(BarEntry(5f,6f))
        entries.add(BarEntry(7f,7f))
        entries.add(BarEntry(9f,8f))
        entries.add(BarEntry(11f,9f))
        entries.add(BarEntry(13f,2f))

        val barDataSet = BarDataSet(entries, "Cells")

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
        barChart.animateY(5000)
    }


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
