package ru.lewis.notes.adapter.listener.impl

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.View.OnClickListener
import ru.lewis.notes.activity.NoteEditorActivity

class FabButtonListener(
    private val context: Context
) : OnClickListener {
    override fun onClick(view: View) {
        val intent = Intent(context, NoteEditorActivity::class.java)
        context.startActivity(intent)
    }
}