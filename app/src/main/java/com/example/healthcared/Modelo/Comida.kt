package com.example.healthcared.Modelo

/**
 * type indica a qué tipo de dieta pertenece
 * time indica a qué hora pertenece la comida
 * ingredients indica los ingredientes, puede ser null
 */
class Comida(val foodName: String, val time: Long, val recipeLink: String, val dia: Long) {
    override fun toString(): String {
        return super.toString()
    }
}