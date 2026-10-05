package com.example.claseuistatemasroom.viewmodel


//Eliminamos
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import com.example.claseuistatemasroom.data.TareasDatabase


//Agregamos

import androidx.lifecycle.ViewModel


import androidx.lifecycle.viewModelScope
import com.example.claseuistatemasroom.data.Tarea
import com.example.claseuistatemasroom.repository.TareasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class TareasViewModel(
    private val repository: TareasRepository
) : ViewModel() {



    // -----------------------------------------
    // UISTATE PRIVADO
    // -----------------------------------------

    private val _uiState =

        MutableStateFlow(
            TareasUiState()
        )


    // -----------------------------------------
    // UISTATE PÚBLICO
    // -----------------------------------------

    val uiState:
            StateFlow<TareasUiState> =

        _uiState.asStateFlow()


    // -----------------------------------------
    // INICIALIZACIÓN
    // -----------------------------------------

    init {

        observarTareas()

    }


    // -----------------------------------------
    // ESCUCHAR CAMBIOS EN ROOM
    // -----------------------------------------

    private fun observarTareas() {

        viewModelScope.launch {


            repository
                .obtenerTareas()
                .collect { lista ->


                    _uiState.update {

                        it.copy(
                            tareas = lista
                        )

                    }

                }

        }

    }


    // -----------------------------------------
    // CAMBIAR TÍTULO
    // -----------------------------------------

    fun cambiarTitulo(
        nuevoTitulo: String
    ) {

        _uiState.update {

            it.copy(

                titulo =
                    nuevoTitulo,

                errorTitulo =
                    null

            )

        }

    }


    // -----------------------------------------
    // CAMBIAR DESCRIPCIÓN
    // -----------------------------------------

    fun cambiarDescripcion(
        nuevaDescripcion: String
    ) {

        _uiState.update {

            it.copy(

                descripcion =
                    nuevaDescripcion

            )

        }

    }


    // -----------------------------------------
    // AGREGAR TAREA
    // -----------------------------------------

    fun agregarTarea() {


        val estadoActual =
            _uiState.value


        // Validar título

        if (
            estadoActual
                .titulo
                .isBlank()
        ) {


            _uiState.update {

                it.copy(

                    errorTitulo =
                        "Ingrese un título"

                )

            }


            return

        }


        // Crear tarea

        val nuevaTarea =

            Tarea(

                titulo =
                    estadoActual
                        .titulo
                        .trim(),

                descripcion =
                    estadoActual
                        .descripcion
                        .trim()

            )


        // Guardar en Room

        viewModelScope.launch {


            repository.insertarTarea(
                nuevaTarea
            )


            // Limpiar formulario

            _uiState.update {

                it.copy(

                    titulo = "",

                    descripcion = "",

                    errorTitulo = null

                )

            }

        }

    }


    // -----------------------------------------
    // ELIMINAR TAREA
    // -----------------------------------------

    fun eliminarTarea(
        tarea: Tarea
    ) {

        viewModelScope.launch {

            repository.elminarTarea(
                tarea
            )

        }

    }

    
    
    fun actualizarTarea(titulo: String, descripcion: String) {
        val seleccionada = uiState.value.tareaSeleccionada?:return
        if(titulo.isBlank()){
            return
        }
        val tareaActualizada = seleccionada.copy(
            titulo = titulo.trim(),
            descripcion = descripcion.trim()
        )
        //Actualización de estado en la pantalla
        viewModelScope.launch { 
            repository.actualizarTarea(tareaActualizada)
            _uiState.update { 
                it.copy(tareaSeleccionada = tareaActualizada)
            }
        }
    }

    // -----------------------------------------
    // CARGAR DETALLE
    // -----------------------------------------

    fun cargarTarea(
        id: Int
    ) {


        _uiState.update {

            it.copy(

                tareaSeleccionada =
                    null,

                cargandoDetalle =
                    true

            )

        }


        viewModelScope.launch {


            val tarea =
                repository.obtenerTareasPorId(id)


            _uiState.update {

                it.copy(

                    tareaSeleccionada =
                        tarea,

                    cargandoDetalle =
                        false

                )

            }

        }

    }

}