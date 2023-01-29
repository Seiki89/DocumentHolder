package com.seiki.android.docholder.screens.work.documents

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class PhotoViewModel: ViewModel() {
    val messagePhoto1 : MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val messagePhoto2 : MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val messagePhoto3 : MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val messagePhoto4 : MutableLiveData<String> by lazy { MutableLiveData<String>() }
}