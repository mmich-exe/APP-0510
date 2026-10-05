package com.example.claseuistatemasroom.viewmodel

import com.example.claseuistatemasroom.data.Tarea


data class TareasUiState(

    val titulo: String = "",

    val descripcion: String = "",

    val tareas: List<Tarea> =
        emptyList(),

    val errorTitulo: String? =
        null,

    val tareaSeleccionada:
    Tarea? = null,

    val cargandoDetalle:
    Boolean = false

)