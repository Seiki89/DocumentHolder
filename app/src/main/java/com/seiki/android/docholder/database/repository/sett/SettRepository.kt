package com.seiki.android.docholder.database.repository.sett

import androidx.lifecycle.LiveData

import com.seiki.android.docholder.model.SettModel

interface SettRepository {
    val getSett: LiveData<List<SettModel>>
    suspend fun insertSett(settModel: SettModel, onSuccess:() -> Unit)
}