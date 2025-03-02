package ru.lewis.notes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.lewis.notes.NoteViewModel
import ru.lewis.notes.R
import ru.lewis.notes.adapter.listener.impl.DeleteButtonListener
import ru.lewis.notes.adapter.listener.impl.EditButtonListener
import ru.lewis.notes.adapter.listener.impl.ShareButtonListener
import ru.lewis.notes.data.Note
import ru.lewis.notes.data.NoteDatabase

class NoteAdapter(
    private var notes: List<Note>,
    private val noteViewModel: NoteViewModel
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.noteTitle)
        val description: TextView = itemView.findViewById(R.id.noteDescription)
        val deleteButton: FloatingActionButton = itemView.findViewById(R.id.deleteButton)
        val editButton: FloatingActionButton = itemView.findViewById(R.id.editButton)
        val shareButton: FloatingActionButton = itemView.findViewById(R.id.shareButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.description.text = note.description

        val context = holder.itemView.context

        holder.deleteButton.setOnClickListener(DeleteButtonListener(note, noteViewModel))
        holder.editButton.setOnClickListener(EditButtonListener(context, note))
        holder.shareButton.setOnClickListener(ShareButtonListener(context, note))
    }

    override fun getItemCount(): Int = notes.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}