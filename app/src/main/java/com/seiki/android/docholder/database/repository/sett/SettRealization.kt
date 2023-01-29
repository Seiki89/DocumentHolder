package com.seiki.android.docholder.database.repository.sett

import androidx.lifecycle.LiveData
import com.seiki.android.docholder.database.DAO
import com.seiki.android.docholder.model.SettModel

class SettRealization (private val Dao: DAO):SettRepository {
    override val getSett: LiveData<List<SettModel>>
        get() = Dao.getSett()

    override suspend fun insertSett(settModel: SettModel, onSuccess: () -> Unit) {
        Dao.insertSett(settModel)
    }
}