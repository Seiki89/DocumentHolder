package com.example.documentholder.misc

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {
    val msgID : MutableLiveData<Int?> by lazy { MutableLiveData<Int?>() }                            //Пересылка id для открытия
    val msgDocumentID : MutableLiveData<Int?> by lazy { MutableLiveData<Int?>() }                            //Пересылка id для открытия


}