package com.example.noteapp


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cafe.adriel.voyager.navigator.Navigator
import com.example.noteapp.models.NoteViewModel
import com.example.noteapp.screens.MainScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val noteViewModel = NoteViewModel(application)
        setContent {
            Navigator(screen = MainScreen(noteViewModel = noteViewModel))

        }
    }
}
