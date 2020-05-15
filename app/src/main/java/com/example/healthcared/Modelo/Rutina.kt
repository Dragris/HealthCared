package com.example.healthcared.Modelo

import android.util.Log
import com.example.healthcared.Controlador
import kotlin.random.*

class Rutina(val rutinaName: String, val difficulty: Long, val obj: String, val daysxweek: Long){
    var lista1: MutableList<Ejercicio> = mutableListOf()
    var lista2: MutableList<Ejercicio> = mutableListOf()
    var lista3: MutableList<Ejercicio> = mutableListOf()
    var lista4: MutableList<Ejercicio> = mutableListOf()
    var lista5: MutableList<Ejercicio> = mutableListOf()
    var lista6: MutableList<Ejercicio> = mutableListOf()
    var lista7: MutableList<Ejercicio> = mutableListOf()

    var sum: Long
    var reps: Long
    var sets: Long
    var rest: Long //segundos
    var ejercicios: MutableList<Ejercicio> = mutableListOf()
    init {
        if (difficulty == 1L) { sum = 3 }
        else if (difficulty == 2L) { sum = 5 }
        else { sum = 9 }
        // Para printear por Logcat -> Log.v("SUM", sum.toString())

        if (obj == "Toy Gordo") {
            ejercicios = Controlador.bajarPeso
            reps = 12
            sets = 3
            rest = 60
        } else if (obj == "Cardio") {
            ejercicios = Controlador.cardio
            reps = 15
            sets = 30 //En cardio son minutos no sets realmente
            rest = 0
        } else if (obj == "Fuelsa") {
            ejercicios = Controlador.ganarFuerza
            reps = 6
            sets = 5
            rest = 120
        } else {
            ejercicios = Controlador.hipertrofia
            reps = 10
            sets = 4
            rest = 90
        }

        val dialibre: MutableList<Ejercicio> = mutableListOf(Ejercicio("Off Day", "https://i.makeagif.com/media/6-20-2015/KVTLDN.gif", 0))
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
                    if (difficulty != 1L) {
                        exercises.add(ejercicios[index])
                        nums.add(index)
                        sum -= ejercicios[index].diff
                    } else {
                        if (ejercicios[index].diff < 2){
                            exercises.add(ejercicios[index])
                            nums.add(index)
                            sum -= ejercicios[index].diff
                        }
                    }
                }
            }
            sum = tmpsum
            tmp.add(exercises)
        }

        var lista: MutableList<MutableList<Ejercicio>> = mutableListOf()
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
        lista1 = lista[0]
        lista2 = lista[1]
        lista3 = lista[2]
        lista4 = lista[3]
        lista5 = lista[4]
        lista6 = lista[5]
        lista7 = lista[6]

        //Log.v("Lista", lista.toString())
    }

    fun getDayByDay(index: Int): MutableList<Ejercicio> {
        return when(index){
            1 -> lista1
            2 -> lista2
            3 -> lista3
            4 -> lista4
            5 -> lista5
            6 -> lista6
            7 -> lista7
            else -> lista1
        }
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