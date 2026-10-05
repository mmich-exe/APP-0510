package com.example.claseuistatemasroom.repository

import com.example.claseuistatemasroom.data.Tarea
import com.example.claseuistatemasroom.data.TareaDao
import kotlinx.coroutines.flow.Flow

class TareasRepository (private val tareasDao: TareaDao) {


    fun obtenerTareas(): Flow<List<Tarea>> {
        return tareasDao.obtenerTareas()
    }

    suspend fun obtenerTareasPorId(id: Int): Tarea? {
        return tareasDao.obtenerTareaPorId(id)
    }

    suspend fun insertarTarea(tarea: Tarea) {
        tareasDao.insertar(tarea)
    }

    suspend fun elminarTarea(tarea: Tarea) {
        tareasDao.eliminar(tarea)
    }

    suspend fun actualizarTarea(tarea: Tarea) {
        tareasDao.actualizar(tarea)
    }










}