package com.seiki.android.docholder.database.repository.pass

import androidx.lifecycle.LiveData
import com.seiki.android.docholder.model.PassModel

interface PassRepository {
    val getPass: LiveData<List<PassModel>>
    suspend fun insertPass(passModel: PassModel,onSuccess:() -> Unit)
    suspend fun deletePass(passModel: PassModel,onSuccess:() -> Unit)
}