package com.example.claseuistatemasroom.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.claseuistatemasroom.data.Tarea


@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun DetalleTareaScreen(

    tarea:
    Tarea?,

    cargando:
    Boolean,

    //Agregamos
    onGuardarCambios:
    (String, String) -> Unit,




    onVolver:
        () -> Unit

) {

//controlamos si estamos en modo edicion
    var editando by rememberSaveable(tarea?.id) {
        mutableStateOf(false)
    }
    //Titulo temporal para edicion
    var tituloEditado by rememberSaveable(tarea?.titulo) {
        mutableStateOf(tarea?.titulo.orEmpty())
    }
//Descripcion temproral para edicion
    var descripcionEditada by rememberSaveable(tarea?.descripcion) {
        mutableStateOf(tarea?.descripcion.orEmpty())
    }


    Scaffold(

        topBar = {


            TopAppBar(

                title = {

                    Text(
                        text = if(!editando){
                            "Detalle de tarea"
                            }else{
                            "Detalle de tarea"
                } )

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
                    24.dp
                )

        ) {


            // -----------------------------------------
            // CARGANDO
            // -----------------------------------------

            if (cargando) {


                CircularProgressIndicator()


            } else if (tarea != null) {
                //modo edicion
                if (editando){
                   //poner cuadros de texto para editar

                    OutlinedTextField(
                        value = tituloEditado,
                        onValueChange = { tituloEditado = it },
                        label = {
                            Text(text = "Titulo")
                        }, singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                        )
                    Spacer(

                        modifier =
                            Modifier.height(
                                16.dp
                            )

                    )

                //Descripcion
                    OutlinedTextField(
                        value = descripcionEditada,
                        onValueChange = { descripcionEditada = it },
                        label = {
                            Text(text = "Descripcion")
                        },
                        minLines = 3,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(

                        modifier =
                            Modifier.height(
                                16.dp
                            )

                    )

                    //Botones en una fila

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        //Cancelar
                        TextButton(onClick = {
                            //Restaurar datos anteriores
                            tituloEditado=tarea.titulo
                            descripcionEditada=tarea.descripcion
                            editando=false

                        }) {
                            Text("Cancelar")
                        }
                        //Guardar cambios
                        TextButton(enabled = tituloEditado.isNotBlank(),
                            onClick = {
                                onGuardarCambios(tituloEditado, descripcionEditada)
                                editando = false
                            }) {
                            Text("Guardar")
                        }
                    }
                } else {
                    // -----------------------------------------
                    // TÍTULO
                    // -----------------------------------------

                    Text(

                        text =
                            tarea.titulo,

                        style =
                            MaterialTheme
                                .typography
                                .headlineSmall

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(onClick = { editando = true }) {
                        Text("Editar")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // -----------------------------------------
                    // DESCRIPCIÓN
                    // -----------------------------------------

                    Text(

                        text =
                            if (
                                tarea.descripcion
                                    .isBlank()
                            ) {

                                "Sin descripción"

                            } else {

                                tarea.descripcion

                            },

                        style =
                            MaterialTheme
                                .typography
                                .bodyLarge

                    )
                }


            } else {


                Text(

                    text =
                        "Tarea no encontrada",

                    style =
                        MaterialTheme
                            .typography
                            .headlineSmall

                )

            }

        }

    }

}