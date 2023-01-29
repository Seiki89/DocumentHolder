package com.seiki.android.docholder

import android.os.Build
import android.os.Bundle
import android.util.Log
import com.seiki.android.docholder.database.repository.card.CardRepository
import com.seiki.android.docholder.database.repository.doc.DocRepository
import com.seiki.android.docholder.database.repository.note.NoteRepository
import com.seiki.android.docholder.database.repository.pass.PassRepository
import com.seiki.android.docholder.database.repository.sett.SettRepository
import com.seiki.android.docholder.screens.work.WorkActivity
import java.io.Serializable

fun LOG(value:String) = Log.d("MyTag", value)

lateinit var APP: WorkActivity

lateinit var REPOSITORY_NOTE: NoteRepository
lateinit var REPOSITORY_PASS: PassRepository
lateinit var REPOSITORY_CARD: CardRepository
lateinit var REPOSITORY_DOC: DocRepository
lateinit var REPOSITORY_SETT: SettRepository

fun <T : Serializable?> Bundle.getSerializableCompat(key: String, clazz: Class<T>): T {
    //проверка версии, перед пробросом
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getSerializable(key, clazz)!!
    else (getSerializable(key) as T)
}
