package com.example.documentholder.document.docs

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.R
import com.example.documentholder.document.ForDoc
import com.example.documentholder.databinding.FragmentDocumentNewBinding
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DiplomaticPassport {

    fun newDiplomaticPassport(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        db: Maindb,
        actionType:String?
    ) {
        ForDoc().visible(activity, view, 1, 9)
        bind.apply {
            txtHeader.text = "Дипломатический паспорт"
            txtInfo1.text = "Номер:"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            txtInfo6.text = "Гражданство:"
            txtInfo7.text = "Кем выдан:"
            txtInfo8.text = "Дата выдачи:"
            txtInfo9.text = "Дата окончания:"

            val idDoc = ForDoc().lifeID(activity)
            if (actionType == "new") { ForDoc().preloadDoc(db, bind) }
            if (actionType == "edit") { bind.imgBackCircle.setImageResource(R.drawable.ic_back_centr) }
            if (actionType == "edit" || actionType == "open") {
                scopeDef.launch {
                    db.getDao().getDoc().forEach {
                        withContext(Dispatchers.Main) {
                            if (it.id == idDoc) {
                                ForDoc().baseLoad(it, bind)
                            }
                        }
                    }
                }
            }
            if (actionType == "open") {
                bind.imgBtnCircle.visibility = View.GONE
                bind.imgSaveIco.setImageResource(R.drawable.ic_back_centr)
                ForDoc().disableText(bind, activity)
            }

            bind.imgSaveBtn.setOnClickListener {
                scopeDef.launch {
                    db.getDao().getDoc().forEach {
                        if (actionType == "new") {
                            if (it.id == 0) {
                                it.id = null
                                it.type = "main"
                                ForDoc().baseSave(it, bind)
                                it.fio = bind.edInput2.text.toString()
                                it.ico = R.drawable.fd_docks_international_pas
                                it.idLD = 7
                                it.bgDoc = R.drawable.gradient_doc_red
                                db.getDao().insertDoc(it)
                            }
                        }
                        if (actionType == "edit") {
                            if (it.id == idDoc) {
                                ForDoc().baseSave(it, bind)
                                db.getDao().insertDoc(it)
                            }
                        }
                    }
                }
                ForDoc().backDock(activity)
            }
        }
    }
}