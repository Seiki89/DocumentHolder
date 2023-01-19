package com.seiki.android.docholder.database.repository.doc

import androidx.lifecycle.LiveData

import com.seiki.android.docholder.model.DocModel

interface DocRepository {
    val getDoc: LiveData<List<DocModel>>
    val checkDoc:Boolean
    suspend fun insertDoc(docModel: DocModel, onSuccess:() -> Unit)
    suspend fun deleteDoc(docModel: DocModel, onSuccess:() -> Unit)
}