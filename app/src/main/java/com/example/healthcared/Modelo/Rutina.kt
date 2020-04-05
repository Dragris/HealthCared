package com.example.healthcared.Modelo

import android.util.Log
import com.example.healthcared.Controlador
import kotlin.random.*

class Rutina(val rutinaName: String, val difficulty: Int, val obj: String, val daysxweek: Int){
    var lista: MutableList<MutableList<Ejercicio>> = mutableListOf()
    var sum: Int
    var ejercicios: MutableList<Ejercicio> = mutableListOf()
    init {
        if (difficulty == 1) { sum = 3 }
        else if (difficulty == 2) { sum = 5 }
        else { sum = 9 }
        // Para printear por Logcat -> Log.v("SUM", sum.toString())

        if (obj == "Toy Gordo") { ejercicios= Controlador.instance.bajarPeso}
        else if (obj == "Cardio") { ejercicios = Controlador.instance.cardio }
        else if (obj == "Fuelsa") { ejercicios = Controlador.instance.ganarFuerza }
        else { ejercicios = Controlador.instance.hipertrofia }



        val dialibre: MutableList<Ejercicio> = mutableListOf(Ejercicio("Dia Libre", "https://i.makeagif.com/media/6-20-2015/KVTLDN.gif", 0))
        var diasLibres = mutableListOf<MutableList<Ejercicio>>()
        for (i in 1..(7-daysxweek)) {
            diasLibres.add(dialibre)
        }
        var tmp: MutableList<MutableList<Ejercicio>> = mutableListOf()

        for (i in 1..daysxweek ){
            var nums = mutableListOf<Int>()
            var exercises = mutableListOf<Ejercicio>()
            val tmpsum = sum

            while (sum > 0) {
                var index = Random.nextInt(0, ejercicios.size)
                if (index !in nums) {
                    exercises.add(ejercicios[index])
                    nums.add(index)
                    sum -= ejercicios[index].diff
                }
            }
            sum = tmpsum
            tmp.add(exercises)
        }

        if (daysxweek <= 3){
            for (i in 1..daysxweek){
                lista.add(tmp[0])
                lista.add(diasLibres[0])
                tmp.removeAt(0)
                diasLibres.removeAt(0)
            }
            while (!diasLibres.isEmpty()) {
                lista.add(diasLibres[0])
                diasLibres.removeAt(0)
            }
        } else {
            for (i in 1..(7-daysxweek)){
                lista.add(tmp[0])
                lista.add(diasLibres[0])
                diasLibres.removeAt(0)
                tmp.removeAt(0)
            }
            while (!tmp.isEmpty()){
                lista.add(tmp[0])
                tmp.removeAt(0)
            }
        }
        Log.v("Lista", lista.toString())
        //TODO() Arreglar logica para que tenga en cuenta la dificultad
    }



    fun display (){/*TODO() mostrar solo los ejercicios del dia correspondiente*/}
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