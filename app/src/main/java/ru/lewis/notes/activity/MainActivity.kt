package ru.lewis.notes.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.lewis.notes.NoteViewModel
import ru.lewis.notes.R
import ru.lewis.notes.adapter.NoteAdapter
import ru.lewis.notes.adapter.listener.impl.FabButtonListener

class MainActivity : AppCompatActivity() {

    private lateinit var noteList: RecyclerView
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteList = findViewById(R.id.recyclerView)
        noteList.layoutManager = LinearLayoutManager(this)

        val fabButton: FloatingActionButton = findViewById(R.id.fab)
        fabButton.setOnClickListener(FabButtonListener(this))

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        val adapter = NoteAdapter(emptyList(), noteViewModel)
        noteList.adapter = adapter

        noteViewModel.allNotes.observe(this) { notes ->
            notes?.let {
                adapter.submitList(it)
            }
        }
    }
}
