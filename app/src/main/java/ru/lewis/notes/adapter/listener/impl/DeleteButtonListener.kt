package ru.lewis.notes.adapter.listener.impl

import android.view.View
import android.view.View.OnClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.lewis.notes.NoteViewModel
import ru.lewis.notes.data.Note

class DeleteButtonListener(
    private val note: Note,
    private val viewModel: NoteViewModel
) : OnClickListener {
    override fun onClick(v: View) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.delete(note)
            withContext(Dispatchers.Main) {}
        }
    }
}