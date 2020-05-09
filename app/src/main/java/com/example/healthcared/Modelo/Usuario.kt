package com.example.healthcared.Modelo

import com.example.healthcared.Controlador


class Usuario(Fullname:String,_Email:String,_Password:String,vegan: Boolean, veget: Boolean) {

    var cont : Long = 1
    var dietas: MutableList<Dieta> = mutableListOf()
    var rutinas: MutableList<Rutina> = mutableListOf()
    var lastDay: Long = 0
    var registroPasos: MutableList<Long> = mutableListOf(0,0,0,0,0,0)
    var numSteps: Long = 0

    //variables del Usuiaro
    var password : String? = null
    var fullname :String? = null
    var email :String? =null
    var gender : String? = null
    var targetSteps : Long? = null
    var height : Long ? = null
    var weight : Long? = null




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
        this.fullname = Fullname
        this.email = _Email
        this.password = _Password
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