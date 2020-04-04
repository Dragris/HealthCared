package com.example.healthcared.Modelo

/**
 * type indica a qué tipo de dieta pertenece
 * time indica a qué hora pertenece la comida
 * ingredients indica los ingredientes, puede ser null
 */
class Comida(val type: Int, val foodName: String, val time: Int, val calories: Int, val ingredients: String?, val vegan: Boolean?) {
    override fun toString(): String {
        return super.toString()
    }
}