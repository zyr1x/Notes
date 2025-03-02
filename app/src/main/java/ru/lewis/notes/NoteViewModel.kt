package ru.lewis.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.lewis.notes.data.Note
import ru.lewis.notes.data.NoteDatabase

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao = NoteDatabase.getDatabase(application).noteDao()
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()
    private val scope: CoroutineScope by lazy { CoroutineScope(Dispatchers.IO) }

    fun insert(note: Note) {
        scope.launch {
            noteDao.insert(note)
        }
    }

    fun delete(note: Note) {
        scope.launch {
            noteDao.deleteNoteById(note.id)
        }
    }

    fun update(note: Note) {
        scope.launch {
            noteDao.update(note)
        }
    }
}