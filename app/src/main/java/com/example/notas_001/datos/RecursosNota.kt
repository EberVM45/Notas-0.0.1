package com.example.notas_001.datos

import android.net.Uri

class RecursosNota(val id: Int, val uri: String, val tipo: String) {
    constructor(uri: String, tipo: String): this(0,uri,tipo)
}