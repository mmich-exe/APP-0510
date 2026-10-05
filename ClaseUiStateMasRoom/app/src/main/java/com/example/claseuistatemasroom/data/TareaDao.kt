package com.example.claseuistatemasroom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TareaDao {


    // -----------------------------------------
    // OBTENER TODAS LAS TAREAS
    // -----------------------------------------

    @Query(
        "SELECT * FROM tareas ORDER BY id DESC"
    )
    fun obtenerTareas():
            Flow<List<Tarea>>


    // -----------------------------------------
    // BUSCAR UNA TAREA POR ID
    // -----------------------------------------

    @Query(
        """
        SELECT * 
        FROM tareas 
        WHERE id = :id 
        LIMIT 1
        """
    )
    suspend fun obtenerTareaPorId(
        id: Int
    ): Tarea?


    // -----------------------------------------
    // INSERTAR
    // -----------------------------------------

    @Insert
    suspend fun insertar(
        tarea: Tarea
    )


    // -----------------------------------------
    // ELIMINAR
    // -----------------------------------------

    @Delete
    suspend fun eliminar(
        tarea: Tarea
    )

    @Update
    suspend fun actualizar(tarea: Tarea)
}