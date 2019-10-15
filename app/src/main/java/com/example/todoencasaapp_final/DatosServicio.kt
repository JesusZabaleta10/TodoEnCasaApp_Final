package com.example.todoencasaapp_final

class DatosServicio (
    var servicio: String,
    var dia: String,
    var hora: String,
    var direccion: String,
    var celular: String,
    var descripcion: String
){
    constructor() : this("","","","","","")
}