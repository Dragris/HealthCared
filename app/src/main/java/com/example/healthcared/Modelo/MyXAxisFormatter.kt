package com.example.healthcared.Modelo

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class MyXAxisFormatter : ValueFormatter() {
    private val days = arrayOf("1 day", "", "2 days", "", "3 days",
        "", "4 days", "", "5 days", "", "6 days")
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}