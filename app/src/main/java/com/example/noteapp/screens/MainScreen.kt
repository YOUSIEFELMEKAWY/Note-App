package com.example.noteapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.noteapp.data.db.Note
import com.example.noteapp.models.NoteViewModel
import kotlinx.coroutines.launch

class MainScreen(val noteViewModel: NoteViewModel) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()
        var showBottomSheet by remember { mutableStateOf(false) }
        val selectedIndex = remember {
            mutableStateOf(0)
        }
        val title = remember { mutableStateOf("") }
        val description = remember { mutableStateOf("") }
        val color = remember { mutableStateOf("") }
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            containerColor = Color.White,

            floatingActionButton =
            {
                IconButton(
                    modifier = Modifier.size(65.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0xFF407BFF),
                        contentColor = Color.White,

                        ),
                    onClick = {
                        showBottomSheet = !showBottomSheet

                    }
                )
                {

                    Icon(
                        modifier = Modifier.size(35.dp),
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                    )

                }
            },
            topBar =
            {
                TopAppBar(
                    title =
                    {
                        Text(
                            text ="Notes",
                            color = Color.Blue,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    ),
                    scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
                )

            },
            bottomBar =
            {

                BottomAppBar(containerColor = Color.White) {
                    Spacer(modifier = Modifier.weight(1f))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(
                            modifier = Modifier.weight(1f),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.White,
                                contentColor =
                                if(selectedIndex.value == 0) Color(0xFF407BFF) else Color.DarkGray
                            ),
                            onClick = {
                                selectedIndex.value = 0
                            }
                        )
                        {

                            Icon(
                                modifier = Modifier.size(35.dp),
                                imageVector = Icons.Default.Home,
                                contentDescription = null,
                            )

                        }
                        Text(text = "Home")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(
                            modifier = Modifier.weight(1f),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.White,
                                contentColor =
                                if(selectedIndex.value == 1) Color(0xFF407BFF) else Color.DarkGray,
                            ),
                            onClick = {
                                selectedIndex.value = 1
                            }
                        )
                        {

                            Icon(
                                modifier = Modifier.size(35.dp),
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                            )

                        }
                        Text(text = "Favorites")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

        ) {

                paddingValues ->

            if(selectedIndex.value == 0)
            {
                HomeScreenContent(
                    noteViewModel = noteViewModel,
                    paddingValues = paddingValues,
                    navigator
                )
            }
            else
            {
                FavoriteScreenContent(noteViewModel = noteViewModel,
                    paddingValues = paddingValues,)
            }
            if(showBottomSheet)
            {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = !showBottomSheet
                    },
                    sheetState = sheetState,
                    dragHandle = {BottomSheetDefaults.DragHandle()},
                    modifier = Modifier.fillMaxSize(),
                    windowInsets = WindowInsets.ime
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Add a note",
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
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF407BFF),
                                contentColor = Color.White
                            ),
                            onClick = {
                            noteViewModel.insertNote(
                                note =  Note(
                                    title = title.value,
                                    description = description.value,
                                    color = color.value
                                )
                            )
                            title.value = ""
                            description.value = ""
                            color.value = ""
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }) {
                            Text("Add")
                        }
                    }


                }
            }

        }
    }
}