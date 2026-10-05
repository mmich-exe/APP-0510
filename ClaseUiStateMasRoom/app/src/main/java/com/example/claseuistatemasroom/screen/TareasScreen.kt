package com.example.claseuistatemasroom.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.claseuistatemasroom.data.Tarea
import com.example.claseuistatemasroom.viewmodel.TareasViewModel


@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun TareasScreen(

    viewModel:
    TareasViewModel,

    onVerDetalle:
        (Int) -> Unit,

    onVolver:
        () -> Unit

) {


    // -----------------------------------------
    // OBSERVAR UISTATE
    // -----------------------------------------

    val uiState by

    viewModel
        .uiState
        .collectAsStateWithLifecycle()


    Scaffold(

        topBar = {


            TopAppBar(

                title = {

                    Text(
                        text =
                            "Mis Tareas"
                    )

                },

                navigationIcon = {


                    TextButton(

                        onClick =
                            onVolver

                    ) {

                        Text(
                            text = "←"
                        )

                    }

                }

            )

        }

    ) { innerPadding ->


        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(
                    innerPadding
                )
                .padding(
                    16.dp
                )

        ) {


            // -----------------------------------------
            // TÍTULO
            // -----------------------------------------

            OutlinedTextField(

                value =
                    uiState.titulo,

                onValueChange = {

                    viewModel
                        .cambiarTitulo(
                            it
                        )

                },

                label = {

                    Text(
                        "Título"
                    )

                },

                isError =
                    uiState
                        .errorTitulo != null,

                supportingText = {


                    uiState
                        .errorTitulo
                        ?.let {

                            Text(
                                text = it
                            )

                        }

                },

                singleLine =
                    true,

                modifier =
                    Modifier
                        .fillMaxWidth()

            )


            Spacer(

                modifier =
                    Modifier.height(
                        8.dp
                    )

            )


            // -----------------------------------------
            // DESCRIPCIÓN
            // -----------------------------------------

            OutlinedTextField(

                value =
                    uiState.descripcion,

                onValueChange = {

                    viewModel
                        .cambiarDescripcion(
                            it
                        )

                },

                label = {

                    Text(
                        "Descripción"
                    )

                },

                minLines = 2,

                modifier =
                    Modifier
                        .fillMaxWidth()

            )


            Spacer(

                modifier =
                    Modifier.height(
                        12.dp
                    )

            )


            // -----------------------------------------
            // BOTÓN AGREGAR
            // -----------------------------------------

            Button(

                onClick = {

                    viewModel
                        .agregarTarea()

                },

                modifier =
                    Modifier
                        .fillMaxWidth()

            ) {

                Text(
                    text =
                        "Agregar tarea"
                )

            }


            Spacer(

                modifier =
                    Modifier.height(
                        20.dp
                    )

            )


            // -----------------------------------------
            // CONTADOR
            // -----------------------------------------

            Text(

                text =
                    "Tareas: ${uiState.tareas.size}",

                style =
                    MaterialTheme
                        .typography
                        .titleLarge

            )


            Spacer(

                modifier =
                    Modifier.height(
                        12.dp
                    )

            )


            // -----------------------------------------
            // LISTA
            // -----------------------------------------

            LazyColumn(

                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),

                verticalArrangement =
                    Arrangement.spacedBy(
                        8.dp
                    )

            ) {


                items(

                    items =
                        uiState.tareas,

                    key = {

                        it.id

                    }

                ) { tarea ->


                    TareaCard(

                        tarea =
                            tarea,

                        onVerDetalle = {

                            onVerDetalle(
                                tarea.id
                            )

                        },

                        onEliminar = {

                            viewModel
                                .eliminarTarea(
                                    tarea
                                )

                        }

                    )

                }

            }

        }

    }

}


// =========================================================
// TARJETA DE TAREA
// =========================================================

@Composable
fun TareaCard(

    tarea: Tarea,

    onVerDetalle:
        () -> Unit,

    onEliminar:
        () -> Unit

) {


    Card(

        modifier =
            Modifier
                .fillMaxWidth()

    ) {


        Column(

            modifier =
                Modifier
                    .padding(
                        16.dp
                    )

        ) {


            Text(

                text =
                    tarea.titulo,

                style =
                    MaterialTheme
                        .typography
                        .titleMedium

            )


            Spacer(

                modifier =
                    Modifier.height(
                        4.dp
                    )

            )


            if (
                tarea.descripcion
                    .isNotBlank()
            ) {

                Text(

                    text =
                        tarea.descripcion,

                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium

                )

            }


            Spacer(

                modifier =
                    Modifier.height(
                        12.dp
                    )

            )


            Row(

                horizontalArrangement =
                    Arrangement.spacedBy(
                        8.dp
                    )

            ) {


                Button(

                    onClick =
                        onVerDetalle

                ) {

                    Text(
                        "Ver detalle"
                    )

                }


                TextButton(

                    onClick =
                        onEliminar

                ) {

                    Text(
                        "Eliminar"
                    )

                }

            }

        }

    }

}