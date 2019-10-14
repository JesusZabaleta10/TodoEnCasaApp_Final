package com.example.todoencasaapp_final

class Usuarios (
    var nombres: String,
    var apellidos: String,
    var correo: String,
    var direccion: String,
    var identificacion: String,
    var celular: String,
    var foto: String
){
    constructor() : this("","","","","","","")
}
