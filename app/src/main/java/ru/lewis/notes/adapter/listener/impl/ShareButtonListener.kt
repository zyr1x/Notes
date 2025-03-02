package ru.lewis.notes.adapter.listener.impl

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.View.OnClickListener
import ru.lewis.notes.data.Note

class ShareButtonListener(
    private val context: Context,
    private val note: Note
) : OnClickListener {

    override fun onClick(v: View?) {
        val shareText = """
            🎉 Вам поделились невероятной заметкой! 📄✨
            
            Заголовок: ${note.title}
            Содержание: ${note.description}
            
            Этот момент не должен остаться незамеченным! 🌟
            Не забывайте, что вы всегда можете вернуться к своей заметке, чтобы найти вдохновение или добавить что-то новое.
            
            Приложение: https://github.com/zyr1x/notes
            🚀 Присоединяйтесь к нашему сообществу и следите за обновлениями!
        """.trimIndent()

        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val chooser = Intent.createChooser(intent, "Поделиться заметкой")
        context.startActivity(chooser)
    }
}
