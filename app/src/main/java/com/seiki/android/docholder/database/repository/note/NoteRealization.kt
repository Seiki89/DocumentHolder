package com.seiki.android.docholder.database.repository.note

import androidx.lifecycle.LiveData
import com.seiki.android.docholder.database.DAO
import com.seiki.android.docholder.model.NoteModel

class NoteRealization(private val Dao: DAO) : NoteRepository {
    override val getNote: LiveData<List<NoteModel>>
        get() = Dao.getNote()

    override suspend fun insertNote(noteModel: NoteModel, onSuccees: () -> Unit) {
        Dao.insertNote(noteModel)
    }

    override suspend fun deleteNote(noteModel: NoteModel, onSuccees: () -> Unit) {
        Dao.deleteNote(noteModel)
    }
}