package com.example.notas_001.datos;

class Tarea (  var idTarea: Int, var titulo: String, var descripcion: String,var FechaNotificacion:String,var HoraNotificacion:String) {
    constructor(titulo: String, descripcion: String,FechaNotificacion: String,HoraNotificacion: String):this(0,titulo, descripcion, FechaNotificacion, HoraNotificacion)
}
