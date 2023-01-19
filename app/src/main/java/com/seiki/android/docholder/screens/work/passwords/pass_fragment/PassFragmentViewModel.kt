package com.seiki.android.docholder.screens.work.passwords.pass_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.seiki.android.docholder.REPOSITORY_NOTE

import com.seiki.android.docholder.REPOSITORY_PASS
import com.seiki.android.docholder.database.MainDb
import com.seiki.android.docholder.database.repository.pass.PassRealization
import com.seiki.android.docholder.model.NoteModel
import com.seiki.android.docholder.model.PassModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PassFragmentViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    fun initDataBase(){
        val daoPass = MainDb.db(context).getDao()
        REPOSITORY_PASS = PassRealization(daoPass)
    }
    fun getAllPass(): LiveData<List<PassModel>> {
        return REPOSITORY_PASS.getPass
    }
    fun delete(passModel: PassModel, onSuccess:() -> Unit ) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY_PASS.deletePass(passModel){}
        }
}