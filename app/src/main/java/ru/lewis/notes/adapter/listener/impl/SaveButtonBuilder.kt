package ru.lewis.notes.adapter.listener.impl

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.lewis.notes.NoteViewModel
import ru.lewis.notes.R
import ru.lewis.notes.data.Note

class SaveButtonBuilder(
    private val viewModel: NoteViewModel,
    private val activity: Activity,
) {
    private var note: Note? = null

    fun setNote(note: Note?): SaveButtonBuilder {
        this.note = note
        return this
    }

    fun build(): SaveButtonListener {
        return SaveButtonListener()
    }

    inner class SaveButtonListener : OnClickListener {
        override fun onClick(view: View) {
            val titleText = activity.findViewById<EditText>(R.id.note_title).text.toString()
            val contentText = activity.findViewById<EditText>(R.id.note_content).text.toString()

            val updatedNote = note?.copy(title = titleText, description = contentText) ?: Note(title = titleText, description = contentText)

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.insert(updatedNote)

                withContext(Dispatchers.Main) {
                    Toast.makeText(activity, "Note saved", Toast.LENGTH_SHORT).show()
                }

                val resultIntent = Intent()
                activity.setResult(RESULT_OK, resultIntent)
                activity.finish()
            }
        }
    }
}
