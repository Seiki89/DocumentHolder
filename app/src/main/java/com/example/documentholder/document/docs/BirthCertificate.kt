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

class BirthCertificate {

    fun newBirthCertificate(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        db: Maindb,
        actionType:String?
    ) {
        ForDoc().visible(activity, view, 1, 13)
        bind.apply {
            txtHeader.text = "Свидетельство о рождении"
            txtInfo1.text = "Серия и номер:"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Дата акта:"
            txtInfo7.text = "Номер акта:"
            divLn7.visibility = View.VISIBLE
            txtInfo8.text = "ФИО отца:"
            txtInfo9.text = "Гражданство отца:"
            divLn9.visibility = View.VISIBLE
            txtInfo10.text = "ФИО матери:"
            txtInfo11.text = "Гражданство матери:"
            divLn11.visibility = View.VISIBLE
            txtInfo12.text = "Место регистрации:"
            txtInfo13.text = "Дата регистрации:"
        }

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
                            it.ico = R.drawable.fd_docks_b_sert
                            it.idLD = 2
                            it.bgDoc = R.drawable.gradient_doc_green
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