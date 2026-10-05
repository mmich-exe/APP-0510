package com.example.claseuistatemasroom.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.claseuistatemasroom.screen.DetalleTareaScreen
import com.example.claseuistatemasroom.screen.InicioScreen
import com.example.claseuistatemasroom.screen.TareasScreen
import com.example.claseuistatemasroom.viewmodel.TareasViewModel


@Composable
fun AppNavigation(tareasViewModel: TareasViewModel) {


    // -----------------------------------------
    // NAVIGATION
    // -----------------------------------------

    val navController =
        rememberNavController()


    // -----------------------------------------
    // VIEWMODEL
    // -----------------------------------------

    val tareasViewModel:
            TareasViewModel =
        viewModel()


    // -----------------------------------------
    // UISTATE
    // -----------------------------------------

    val uiState by

    tareasViewModel
        .uiState
        .collectAsStateWithLifecycle()


    // -----------------------------------------
    // NAVHOST
    // -----------------------------------------

    NavHost(

        navController =
            navController,

        startDestination =
            Rutas.INICIO

    ) {


        // =================================================
        // INICIO
        // =================================================

        composable(
            route =
                Rutas.INICIO
        ) {


            InicioScreen(

                onVerTareas = {


                    navController
                        .navigate(
                            Rutas.TAREAS
                        )

                }

            )

        }


        // =================================================
        // TAREAS
        // =================================================

        composable(
            route =
                Rutas.TAREAS
        ) {


            TareasScreen(

                viewModel =
                    tareasViewModel,

                onVerDetalle = { tareaId ->


                    navController
                        .navigate(

                            Rutas.detalle(
                                tareaId
                            )

                        )

                },

                onVolver = {


                    navController
                        .popBackStack()

                }

            )

        }


        // =================================================
        // DETALLE
        // =================================================

        composable(

            route =
                Rutas.DETALLE,

            arguments =
                listOf(

                    navArgument(
                        "tareaId"
                    ) {

                        type =
                            NavType.IntType

                    }

                )

        ) { backStackEntry ->


            // -----------------------------------------
            // OBTENER ID
            // -----------------------------------------

            val tareaId =

                backStackEntry
                    .arguments
                    ?.getInt(
                        "tareaId"
                    )
                    ?: 0


            // -----------------------------------------
            // CARGAR TAREA UNA SOLA VEZ POR ID
            // -----------------------------------------

            LaunchedEffect(
                tareaId
            ) {

                tareasViewModel
                    .cargarTarea(
                        tareaId
                    )

            }


            // -----------------------------------------
            // DETALLE
            // -----------------------------------------

            DetalleTareaScreen(

                tarea =
                    uiState
                        .tareaSeleccionada,

                cargando =
                    uiState
                        .cargandoDetalle,

                onGuardarCambios = { titulo, descripcion ->
                    tareasViewModel.actualizarTarea(titulo, descripcion)
                },

                onVolver = {


                    navController
                        .popBackStack()

                }

            )

        }

    }

}