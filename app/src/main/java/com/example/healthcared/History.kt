package com.example.healthcared

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

import kotlinx.android.synthetic.main.activity_history.*

class History : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)


        setBarChart()
        }


    private fun setBarChart() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1f,0f)) //X es la ubicación de la barra (cada 2 para dejar espacio)
        entries.add(BarEntry(3f,4f)) //Y es hasta dónde llega la barra
        entries.add(BarEntry(5f,6f)) //Buscar funcion on click de la barra para actualizar mostrado de datos
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

        val data = BarData(barDataSet) //,labels
        barChart.data = data // set the data and list of lables into chart

        //barChart.setDescription("Set Bar Chart Description")  // set the description

        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = resources.getColor(R.color.colorAccent)

        barChart.setScaleEnabled(false);
        barChart.animateY(5000)
    }

}
