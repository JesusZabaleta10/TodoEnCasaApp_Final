package com.example.todoencasaapp_final

class Tecnicos (
    var nombres: String,
    var apellidos: String,
    var fecha_nacimiento: String,
    var identificacion: String,
    var celular: String,
    var correo: String,
    var descripcion: String,
    var experiencia: String,
    var horario: String,
    var direccion: String,
    var categoria: String,
    var subcatergoria: String,
    var foto: String,
    var foto_certificado: String
){
    constructor() : this("","","","","","","","","","","","","","")
}