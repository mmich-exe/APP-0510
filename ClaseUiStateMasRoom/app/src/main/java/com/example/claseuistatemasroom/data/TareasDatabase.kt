package com.example.claseuistatemasroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(

    entities = [
        Tarea::class
    ],

    version = 1,

    exportSchema = false

)
abstract class TareasDatabase :
    RoomDatabase() {


    // -----------------------------------------
    // DAO
    // -----------------------------------------

    abstract fun tareaDao():
            TareaDao


    companion object {


        // -----------------------------------------
        // INSTANCIA ÚNICA
        // -----------------------------------------

        @Volatile
        private var INSTANCE:
                TareasDatabase? = null


        // -----------------------------------------
        // OBTENER BASE DE DATOS
        // -----------------------------------------

        fun getDatabase(
            context: Context
        ): TareasDatabase {


            return INSTANCE
                ?: synchronized(this) {


                    val instancia =

                        Room.databaseBuilder(

                            context.applicationContext,

                            TareasDatabase::class.java,

                            "tareas_database"

                        ).build()


                    INSTANCE =
                        instancia


                    instancia

                }

        }

    }

}