package com.example.healthcared

import com.example.healthcared.Modelo.Comida
import com.example.healthcared.Modelo.Dieta
import com.example.healthcared.Modelo.Ejercicio
import com.example.healthcared.Modelo.Rutina

class Controlador {
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
}