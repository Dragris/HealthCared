package com.example.healthcared

import com.example.healthcared.Modelo.*
import com.example.healthcared.UI.LogIn
import com.google.firebase.auth.FirebaseAuth

open class Controlador private constructor () {
    private object HOLDER {
        val INSTANCE = Controlador()
    }
    companion object {
        val instance: Controlador by lazy { HOLDER.INSTANCE }
    }

    val pushup = Ejercicio("Push-Ups", "https://www.youtube.com/watch?v=yXuYJtRkmlM", 1)
    val squats = Ejercicio("Squats", "https://www.youtube.com/watch?v=C_VtOYc6j5c", 1)
    val squatsandjump = Ejercicio("Jump Squats", "https://www.youtube.com/watch?v=Azl5tkCzDcc", 2)
    val benchdip = Ejercicio("Bench Dips", "https://www.youtube.com/watch?v=c3ZGl4pAwZ4", 1)
    val diamonpushup = Ejercicio("Diamond Push-Ups", "https://www.youtube.com/watch?v=J0DnG1_S92I", 2)
    val plank = Ejercicio("Plank", "https://www.youtube.com/watch?v=pSHjTRCQxIw", 2)
    val legraise = Ejercicio("Leg Raises", "https://www.youtube.com/watch?v=l4kQd9eWclE", 2)
    val crunch = Ejercicio("Normal Crunch", "https://www.youtube.com/watch?v=NGRKFMKhF8s", 1)
    val jumpjacks = Ejercicio("Jumping Jacks", "https://www.youtube.com/watch?v=c4DAnQ6DtF8", 1)
    val hipraise = Ejercicio("Hip Raises", "https://www.youtube.com/watch?v=fDP6O_aJpDg", 1)
    val mountclimbers = Ejercicio("Mountain Climbers", "https://www.youtube.com/watch?v=De3Gl-nC7IQ", 2)
    val burpee = Ejercicio("Burpees", "https://www.youtube.com/watch?v=NCqbpkoiyXE", 2)

    var ganarFuerza: MutableList<Ejercicio> = mutableListOf(pushup, squats, squatsandjump, benchdip, diamonpushup, plank, legraise, crunch)
    var bajarPeso: MutableList<Ejercicio> = mutableListOf(jumpjacks, squats, pushup, hipraise, crunch, plank)
    var hipertrofia: MutableList<Ejercicio> = mutableListOf(pushup, squats, plank, legraise, crunch, benchdip, diamonpushup)
    var cardio: MutableList<Ejercicio> = mutableListOf(jumpjacks, squats, burpee, mountclimbers)


    //Contnedores personales, cargar con log-in
    var listaDieta: MutableCollection<Dieta>? =null
    var listaRutina: MutableCollection<Rutina>? =null
    //var usuario: Usuario  //En caso de ser necesario mantener qué usuario es el que está conectado
    var pasos: MutableCollection<Int>? =null

    //Contenedores de la app, cargar con inicio de la app
    var ejercicios: MutableCollection<Ejercicio>? =null
    var comidas: MutableCollection<Comida>? =null


    fun cargarDatos(){

        TODO()
        //Implementación de una carga de datos a las listas cuando se haga login
        //Puede ser init de la clase
    }

    fun guardarDatos(){
        TODO()
        //Guardar datos en firebase para el usuario, borrarlos del dispositivo.
    }

    fun mockUp(){
        TODO()
        //Mock up para probar datos y etc.

    }

  //  fun SinginWuthEmailAndPassword(e:String,x:String,){
    //    FirebaseAuth.getInstance().signInWithEmailAndPassword(e,x)
   //         .addOnCompleteListener{
    //            if(it.isSuccessful){

   //             }
  //          }
  //  }


}