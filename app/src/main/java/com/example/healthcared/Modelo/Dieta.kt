package com.example.healthcared.Modelo

import android.util.Log
import com.example.healthcared.Modelo.Comida

class Dieta(val name: String, val comidas: MutableList<Comida>) {
    init{
    }
}
/*
A la espera de si hacemos 1 dieta o varias

if (B1 == False) {
    //damos acceso a dietas vegetarianas
}
if (B2 == True) {
    //damos acceso a dietas veganas
} else {
    //damos acceso a dietas normales
}
User Normal -> False, False
User Vegetariano -> False True
User Vegano -> True True
User que no ha respondido -> null null o True False
 */