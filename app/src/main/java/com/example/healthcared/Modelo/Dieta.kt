package com.example.healthcared.Modelo

import com.example.healthcared.Modelo.Comida

class Dieta(val dietName: String) {
    val lista: MutableCollection<Comida>? = null

    fun createDieta(type: Int, vegan: Boolean, dietName: String){
        //TODO() Crear lógica de selección de comidas
    }

    fun display(){
        //TODO selecciona una dieta diaria que vaya rotando
        //Va seleccionando según el día de la semana
    }
}