package com.example.healthcared.Modelo

import android.provider.ContactsContract

class Usuario(Fullname:String,_Email:String,_Username:String,_Weight:String,_Height:String) {
    var rutinas: MutableList<Rutina> = mutableListOf()

    lateinit var name: String
    lateinit var mail: String
    lateinit var birth: String
    var height: Float = 0.0f
    var weight: Float = 0.0f

    init {

    }
    
}