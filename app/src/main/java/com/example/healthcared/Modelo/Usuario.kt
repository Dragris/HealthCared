package com.example.healthcared.Modelo
import android.util.Log
import com.example.healthcared.Controlador
class Usuario(Fullname:String,_Email:String,_Username:String,_Password:String,vegan: Boolean, veget: Boolean) {

    var cont = 1
    var dietas: MutableList<Dieta> = mutableListOf()
    var rutinas: MutableList<Rutina> = mutableListOf()
    var lastDay: Int = 0
    var registroPasos: MutableList<Int> = mutableListOf(0,0,0,0,0,0)
    var numSteps: Int = 0

    //variables del Usuiaro
    var password : String? = null
    var fullname :String? = null
    var email :String? =null
    var username:String? = null
    var gender : String? = null
    get() = field
    set(value) {
        field = value
    }
    var bDate : String ? = null
    get() = field
    set(value) {
        field = value
    }

    var height : Int ? = null
    get() = field
    set(value) {
        field = value
    }
    var wieght : Int? = null
    get() = field
    set(value) {
        field = value
    }



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
        this.username = _Username
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