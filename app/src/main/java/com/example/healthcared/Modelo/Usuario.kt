package com.example.healthcared.Modelo

class Usuario(Fullname:String,_Email:String,_Username:String,_Password:String) {
    var rutinas: MutableList<Rutina> = mutableListOf()
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
    var age : Int ? = null
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
        this.fullname = Fullname
        this.email = _Email
        this.username = _Username
        this.password = _Password
    }
    
}