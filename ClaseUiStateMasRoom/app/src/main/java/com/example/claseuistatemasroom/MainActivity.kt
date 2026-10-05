package com.example.claseuistatemasroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.claseuistatemasroom.data.TareasDatabase
import com.example.claseuistatemasroom.navigation.AppNavigation
import com.example.claseuistatemasroom.repository.TareasRepository
import com.example.claseuistatemasroom.ui.theme.ClaseUiStateMasRoomTheme
import com.example.claseuistatemasroom.viewmodel.TareasViewModel
import com.example.claseuistatemasroom.viewmodel.TareasViewModelFactory

class MainActivity : ComponentActivity() {
    //database
    private val database by lazy {
        TareasDatabase.getDatabase(applicationContext)
    }

    //Repository
    private val repository by lazy {
        TareasRepository(database.tareaDao())
    }

    //Viewmodel
    private val tareasViewModel: TareasViewModel by viewModels {
        TareasViewModelFactory(repository)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClaseUiStateMasRoomTheme {
               AppNavigation(tareasViewModel=tareasViewModel)
                }
            }
        }
    }


