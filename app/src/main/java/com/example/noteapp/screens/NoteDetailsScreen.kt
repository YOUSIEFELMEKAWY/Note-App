package com.example.noteapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.noteapp.data.db.Note
import com.example.noteapp.models.NoteViewModel
import kotlinx.coroutines.launch

class NoteDetailsScreen(val note: Note,val noteViewModel: NoteViewModel) : Screen
{
    @Composable
    override fun Content() {
        val title = remember { mutableStateOf(note.title) }
        val description = remember { mutableStateOf(note.description) }
        val color = remember { mutableStateOf(note.color) }
        val navigator = LocalNavigator.currentOrThrow
        Column(modifier = Modifier.padding(20.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
    Text(
        color = Color(0xFF407BFF),
        text = "Edit note",
        fontSize = 25.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold

    )
    Spacer(modifier = Modifier.size(10.dp))
    OutlinedTextField(
        label = { Text(text = "Title") },
        modifier = Modifier.fillMaxWidth(),
        value = title.value,
        onValueChange ={title.value = it },
        placeholder = { Text(text = "Add Title") }
    )
    Spacer(modifier = Modifier.size(10.dp))
    OutlinedTextField(
        label = { Text(text = "Color") },
        modifier = Modifier.fillMaxWidth(),
        value = color.value,
        onValueChange ={color.value = it },
        placeholder = { Text(text = "Add Color") }
    )
    Spacer(modifier = Modifier.size(10.dp))
    OutlinedTextField(
        label = { Text(text = "Description") },
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        value = description.value,
        onValueChange ={description.value = it },
        placeholder = { Text(text = "Add Description") }
    )

    Spacer(modifier = Modifier.size(20.dp))
            Row (){
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF407BFF),
                        contentColor = Color.White
                    ),
                    onClick = {
                    noteViewModel.updateNote(
                        note.copy(
                            title = title.value,
                            description = description.value,
                            color = color.value
                        )
                    )
                    navigator.pop()
                }) {
                    Text("Update")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF407BFF),
                        contentColor = Color.White
                    ),
                    onClick = {
                    noteViewModel.deleteNote(
                        note
                    )
                    navigator.pop()

                }) {
                    Text("Delete")
                }

            }
}
}

}