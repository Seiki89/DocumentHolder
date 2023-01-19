package com.seiki.android.docholder.database.repository.doc

import androidx.lifecycle.LiveData
import com.seiki.android.docholder.database.DAO
import com.seiki.android.docholder.model.DocModel

class DocRealization(private val Dao: DAO) : DocRepository {
    override val getDoc: LiveData<List<DocModel>>
        get() = Dao.getDoc()

    override val checkDoc:Boolean
        get() =  Dao.isEmptyDoc()


    override suspend fun insertDoc(docModel: DocModel, onSuccess: () -> Unit) {
        Dao.insertDoc(docModel)
    }

    override suspend fun deleteDoc(docModel: DocModel, onSuccess: () -> Unit) {
        Dao.deleteDoc(docModel)
    }

}