package com.example.healthcared.Modelo

import kotlin.math.round

/**
 * type indica el tipo de rutina a la que pertenece el ejercicio.
 * body indica la zona (superior, inferior, troncal) que ejercita el ejercicio.
 * repeatFactor es un factor añadido para calcular las repeticiones que harán falta por serie.
 */
class Ejercicio(val type: Int, val body: String, val ExerciceName:String, val youtubeLink: String, val repeatFactor: Float) {

    fun getRepeat(level: Int): Int {
        return round(repeatFactor*5.0*level).toInt()
    }
}