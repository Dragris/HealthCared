package com.example.healthcared.Modelo

import kotlin.math.round

/**
 * type indica el tipo de rutina a la que pertenece el ejercicio.
 * body indica la zona (superior, inferior, troncal) que ejercita el ejercicio.
 * repeatFactor es un factor añadido para calcular las repeticiones que harán falta por serie.
 */
class Ejercicio(val type: Int, val body: String, val ExerciceName:String, val youtubeLink: String, val diff: Int) {


}

/*
Type
Flexiones = 7
Sentadillas = 15
Sentadillas + salto = 1
Bench Dip = 5
Flexiones de Triceps = 5
Plank = 7
Leg Rise = 5
Crunch = 7
Jumping Jacks = 10
Elevaciones de cadera = 2
Mountain Climbers = 8
Burpee =  8



- Ejercicio (Dificultad)
Tipo 1 (Ganar Fuerza)
- Flexiones (1)
- Sentadillas (1)
- Sentadillas + Salto (2)
- Bench Dip (Triceps) (1)
- Flexiones de Triceps (2)
- Abdominales:
    Plank (2), Elevaciones de piernas (2), Crunch (1)

Tipo 2 (Bajar de Peso)
- Jumping Jacks (1)
- Sentadillas (1)
- Flexiones (1)
- Elevaciones de cadera (1)
- Crunch (1)
- Plank (2)

Tipo 3 (Hipertrofia)
- Flexiones (1)
- Sentadillas (1) (Con peso si puede ser (2))
- Abdominales:
    Plank (2), Elevaciones de piernas (2), Crunch (1)
- Bench Dip (Triceps) (1)
- Flexiones de Triceps (2)

Tipo 4 (Cardio)
- Jumping Jacks (1)
- Sentadilla (1)
- Burpee (2)
- Mountain Climbers (2)
 */