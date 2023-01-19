package com.seiki.android.docholder.screens.work.documents

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.seiki.android.docholder.REPOSITORY_DOC
import com.seiki.android.docholder.database.MainDb
import com.seiki.android.docholder.database.repository.doc.DocRealization
import com.seiki.android.docholder.model.DocModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DocumentViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    fun initDataBase() {
        val daoDoc = MainDb.db(context).getDao()
        REPOSITORY_DOC = DocRealization(daoDoc)
    }
    fun insert(docModel: DocModel) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY_DOC.insertDoc(docModel){}
        }

    fun getAllDocs(): LiveData<List<DocModel>> {
        return REPOSITORY_DOC.getDoc
    }

    fun checkDoc():Boolean{
        return REPOSITORY_DOC.checkDoc
    }

    fun delete(docModel: DocModel, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY_DOC.deleteDoc(docModel) {}
        }

}