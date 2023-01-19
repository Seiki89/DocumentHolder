package com.seiki.android.docholder.database.repository.note

import androidx.lifecycle.LiveData
import com.seiki.android.docholder.model.NoteModel

interface NoteRepository {
    val getNote: LiveData<List<NoteModel>>
    suspend fun insertNote(noteModel: NoteModel,onSuccess:() -> Unit)
    suspend fun deleteNote(noteModel: NoteModel,onSuccess:() -> Unit)
}