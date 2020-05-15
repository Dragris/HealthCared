package com.example.healthcared.Modelo

import kotlin.math.round

/**
 * type indica el tipo de rutina a la que pertenece el ejercicio.
 * body indica la zona (superior, inferior, troncal) que ejercita el ejercicio.
 * repeatFactor es un factor añadido para calcular las repeticiones que harán falta por serie.
 */
class Ejercicio(val ExerciceName:String, val youtubeLink: String, val diff: Long) {
    override fun toString(): String {
        var nombre = ExerciceName + " " + diff.toString()
        return nombre
    }
}

/*
Ganar Fuerza +1
Bajar Peso +2
Hipertrofia +4
Cardio +8

Ganar Fuerza: Flexiones, Sentadillas, Sentadillas + Salto, Bench Dip, Flexiones de Triceps, Plank, Elevaciones de piernas, Crunch
Bajar de Peso: Jumping Jacks, Sentadillas, Flexiones, Elevaciones de cadera, Crunch, Plank
Hipertrofia: Flexiones, Sentadillas, Plank, Elevaciones de piernas, Crunch, Bench Dip, Flexiones de Triceps
Cardio: Jumping Jacks, Sentadilla, Burpee, Mountain Climbers

Type
Flexiones = 7
Sentadillas = 15
Sentadillas + salto = 1
Bench Dip = 5
Flexiones de Triceps = 5
Plank = 7
Leg Rise = 5
Crunch = 7
Jumping Jacks = 10
Elevaciones de cadera = 2
Mountain Climbers = 8
Burpee =  8
 */