package com.seiki.android.docholder.screens.work.notes.note_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.seiki.android.docholder.REPOSITORY_NOTE
import com.seiki.android.docholder.database.MainDb
import com.seiki.android.docholder.database.repository.note.NoteRealization
import com.seiki.android.docholder.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    fun initDataBase(){
        val daoNote = MainDb.db(context).getDao()
        REPOSITORY_NOTE = NoteRealization(daoNote)
    }
    fun getAllNotes(): LiveData<List<NoteModel>> {
        return REPOSITORY_NOTE.getNote
    }
    fun delete(noteModel: NoteModel, onSuccess:() -> Unit ) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY_NOTE.deleteNote(noteModel){}
        }

}