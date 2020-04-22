package com.example.healthcared.Modelo

import android.util.Log
import com.example.healthcared.Controlador

class Usuario(Fullname: String, _Email: String, _Username: String, _Weight: String, _Height: String, vegan: Boolean, veget: Boolean) {
    var cont = 1
    var dietas: MutableList<Dieta> = mutableListOf()
    var rutinas: MutableList<Rutina> = mutableListOf()


    init {
        if (!vegan && !veget) {
            dietas.add(Controlador.normal)
            dietas.add(Controlador.veget)
        } else if (!vegan && veget) {
            dietas.add(Controlador.veget)
            dietas.add(Controlador.vegan)
        } else {
            dietas.add(Controlador.vegan)
        }
    }

    fun findRutinaByName(name: String): Rutina{
        var toReturn: Rutina = Rutina("a", 0, "a", 1)
        for (i in rutinas){
            if (i.rutinaName == name){
                toReturn = i
            }
        }
        return toReturn
    }
}