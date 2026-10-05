package com.example.claseuistatemasroom.navigation

object Rutas {


    const val INICIO =
        "inicio"


    const val TAREAS =
        "tareas"


    const val DETALLE =
        "detalle/{tareaId}"


    fun detalle(
        tareaId: Int
    ): String {

        return "detalle/$tareaId"

    }

}