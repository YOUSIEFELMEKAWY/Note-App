package com.example.noteapp.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.db.Note
import com.example.noteapp.data.db.NoteDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) :AndroidViewModel(application) {

    val noteDao = NoteDatabase.getDatabase(application).noteDao()


    private val _allNotes = MutableStateFlow<List<Note>?>(null)
    val allNotes : StateFlow<List<Note>?> get() = _allNotes



    init {
        getAllNotes()
    }



    fun getAllNotes() {
        viewModelScope.launch {
            _allNotes.value = noteDao.getAllNotes()
            println("All notes: ${_allNotes.value}")
        }
    }
    fun insertNote(note: Note){
        viewModelScope.launch {
            noteDao.insert(note)
            getAllNotes()
        }
    }

    fun updateNote(note:Note){
        viewModelScope.launch {
            noteDao.update(note)
            getAllNotes()

        }
    }

    fun getNoteById(id:Int){
        viewModelScope.launch {
            noteDao.getById(id)
            getAllNotes()
        }
    }

    fun deleteNote(note:Note){
        viewModelScope.launch {
            noteDao.deleteNote(note)
            getAllNotes()
        }
    }


}