package com.seiki.android.docholder.screens.work.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.seiki.android.docholder.REPOSITORY_SETT
import com.seiki.android.docholder.database.MainDb
import com.seiki.android.docholder.database.repository.sett.SettRealization
import com.seiki.android.docholder.model.SettModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    fun initDataBase() {
        val daoSett = MainDb.db(context).getDao()
        REPOSITORY_SETT = SettRealization(daoSett)
    }

    fun insert(settModel: SettModel) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY_SETT.insertSett(settModel) {}
        }

    fun getAllSett(): LiveData<List<SettModel>> {
        return REPOSITORY_SETT.getSett
    }
}