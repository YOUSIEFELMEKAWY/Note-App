package com.example.noteapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import com.example.noteapp.SwipeToDeleteContainer
import com.example.noteapp.models.NoteViewModel

@Composable
fun HomeScreenContent(
    noteViewModel: NoteViewModel,
    paddingValues: PaddingValues,
    navigator: Navigator,
)
{
    val allNotes = noteViewModel.allNotes.collectAsState()

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(paddingValues)) {
        items(allNotes.value ?: emptyList())
        {
            note ->
            SwipeToDeleteContainer(
                item = allNotes,
                onDelete = {noteViewModel.deleteNote(note)}
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors =  CardDefaults.cardColors(
                        ColorFromUserInput(userInput = note.color)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigator.push(NoteDetailsScreen(note, noteViewModel))
                        }
                        .padding(10.dp)
                        .fillMaxSize()
                ) {
                    Column(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .fillMaxSize()) {

                        Text(
                            text = note.title,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Icon(
                            modifier = Modifier
                                .clickable {
                                    noteViewModel.updateNote(note.copy(isFavorite = !note.isFavorite!!))
                                }
                                .align(Alignment.End)
                                .size(30.dp),
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = if (note.isFavorite == true) Color.Yellow else  Color.White)


                        Spacer(modifier = Modifier.height(15.dp))
                        Text(text = note.description)

                    }
                }

            }


        }
    }
    }



fun ColorFromUserInput(userInput: String): Color {
    val color = when (userInput.trim().lowercase()) {
        "blue" -> Color.Blue
        "red" -> Color.Red
        "green" -> Color.Green
        "yellow" -> Color.Yellow
        // Add more colors as needed
        else -> Color.Gray // Default color if the input doesn't match
    }
    return color
}