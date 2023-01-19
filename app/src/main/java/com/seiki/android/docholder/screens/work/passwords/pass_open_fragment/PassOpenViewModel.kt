package com.seiki.android.docholder.screens.work.passwords.pass_open_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seiki.android.docholder.REPOSITORY_PASS
import com.seiki.android.docholder.model.PassModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PassOpenViewModel:ViewModel() {
    fun insert(passModel: PassModel, onSuccess:() -> Unit ) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY_PASS.insertPass(passModel){}
        }
}