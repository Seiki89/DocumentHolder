package com.seiki.android.docholder.screens.work.notes.note_new_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seiki.android.docholder.REPOSITORY_NOTE
import com.seiki.android.docholder.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteNewViewModel: ViewModel() {
    fun insert(noteModel: NoteModel, onSuccess:() -> Unit ) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY_NOTE.insertNote(noteModel){}
        }
}