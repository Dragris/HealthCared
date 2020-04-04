package com.example.healthcared.Modelo

class Rutina(val rutinaName: String) {
    val lista: MutableCollection<Ejercicio>? = null


    fun createRutina(type: Int, habilidad: Int){
        TODO()//Crear lógica de selección de ejercicios
    }


    /* Crear Rutina pautas:
    Bajar Peso:
        - 3 series
        - 10-15 reps
        - 60s de rest

    Ganar Fuerza:
        - 4-5 series
        - 5-6 reps (max intensity)
        - 120s de rest

    Ponerse mamadisimo:
        - 4 series
        - 8-12 reps
        - 90s de rest

    Cardio/Tonificar:
        - Repetir series de ejercicios durante x mins
        - Series de 10-20 reps
        - Max intensity
     */
}