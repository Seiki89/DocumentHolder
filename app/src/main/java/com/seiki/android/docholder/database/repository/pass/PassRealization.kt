package com.seiki.android.docholder.database.repository.pass

import androidx.lifecycle.LiveData
import com.seiki.android.docholder.database.DAO
import com.seiki.android.docholder.model.PassModel

class PassRealization(private val Dao: DAO) : PassRepository {
    override val getPass: LiveData<List<PassModel>>
        get() = Dao.getPass()

    override suspend fun insertPass(passModel: PassModel, onSuccess: () -> Unit) {
        Dao.insertPass(passModel)
    }

    override suspend fun deletePass(passModel: PassModel, onSuccess: () -> Unit) {
        Dao.deletePass(passModel)
    }
}