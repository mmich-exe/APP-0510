package com.example.claseuistatemasroom.screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun InicioScreen(

    onVerTareas:
        () -> Unit

) {


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement =
            Arrangement.Center

    ) {


        Text(

            text =
                "Mis Tareas",

            style =
                MaterialTheme
                    .typography
                    .headlineLarge

        )


        Spacer(

            modifier =
                Modifier.height(
                    12.dp
                )

        )


        Text(

            text =
                "Organiza tus actividades y guárdalas permanentemente con Room.",

            style =
                MaterialTheme
                    .typography
                    .bodyLarge

        )


        Spacer(

            modifier =
                Modifier.height(
                    24.dp
                )

        )


        Button(

            onClick =
                onVerTareas,

            modifier =
                Modifier
                    .fillMaxWidth()

        ) {


            Text(
                text =
                    "Ver mis tareas"
            )

        }

    }

}