package com.example.notas_001.datos

class Nota (  var idNota: Int, var titulo: String, var descripcion: String) {
    constructor(titulo: String, descripcion: String):this(0,titulo, descripcion)
}