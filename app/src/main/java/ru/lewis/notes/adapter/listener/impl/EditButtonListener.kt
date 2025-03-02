package ru.lewis.notes.adapter.listener.impl

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.View.OnClickListener
import ru.lewis.notes.activity.NoteEditorActivity
import ru.lewis.notes.data.Note

class EditButtonListener(
    private val context: Context,
    private val note: Note
) : OnClickListener {
    override fun onClick(v: View?) {
        val intent = Intent(context, NoteEditorActivity::class.java)

        intent.putExtra("note", note)

        context.startActivity(intent)
    }
}