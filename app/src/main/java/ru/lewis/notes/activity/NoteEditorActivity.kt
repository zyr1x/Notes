package ru.lewis.notes.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.lewis.notes.NoteViewModel
import ru.lewis.notes.R
import ru.lewis.notes.adapter.listener.impl.SaveButtonBuilder
import ru.lewis.notes.data.Note

class NoteEditorActivity : AppCompatActivity() {
    private lateinit var saveButton: Button
    private lateinit var noteViewModel: NoteViewModel

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        saveButton = findViewById(R.id.save_note_button)

        val cacheNote = intent.getParcelableExtra("note", Note::class.java)
        cacheNote?.let { apply(this, it) }

        val saveButtonListener = SaveButtonBuilder(noteViewModel, this)
            .setNote(cacheNote)
            .build()

        saveButton.setOnClickListener(saveButtonListener)
    }

    private fun apply(activity: Activity, note: Note) {
        val titleEditText = activity.findViewById<EditText>(R.id.note_title)
        val contentEditText = activity.findViewById<EditText>(R.id.note_content)

        titleEditText.setText(note.title)
        contentEditText.setText(note.description)
    }
}