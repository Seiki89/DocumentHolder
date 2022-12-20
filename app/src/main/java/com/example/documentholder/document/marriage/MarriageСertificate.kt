package com.example.documentholder.document.marriage

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.R
import com.example.documentholder.databinding.FragmentDocumentNewBinding
import com.example.documentholder.document.ForDoc
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarriageСertificate {

    fun new(activity: FragmentActivity, bind: FragmentDocumentNewBinding, view: View, db: Maindb,
    actionType:String?) {
        ForDoc().visible(activity, view, 1,12)
        bind.apply {
            txtHeader.text = "Свидетельство о заключении брака"
            txtInfo1.text = "Серия/номер свидетельства:"
            txtInfo2.text = "Дата заключения брака:"
            txtInfo3.text = "Дата акта о заключении брака:"
            txtInfo4.text = "Номер записи акта:"
            divLn4.visibility = View.VISIBLE
            txtInfo5.text = "МУЖ:"
            edInput5.visibility = View.GONE
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "ФИО:"
            txtInfo7.text = "Дата рождения:"
            txtInfo8.text = "Место рождения:"
            divLn8.visibility = View.VISIBLE
            txtInfo9.text = "ЖЕНА"
            edInput9.visibility = View.GONE
            divLn9.visibility = View.VISIBLE
            txtInfo10.text = "ФИО:"
            txtInfo11.text = "Дата рождения:"
            txtInfo12.text = "Место рождения:"
        }

        val idDoc = ForDoc().lifeID(activity)
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
                            it.type = "marriage"
                            ForDoc().baseSave(it, bind)
                            it.fio = ""
                            it.ico = R.drawable.fd_marriage_marr
                            it.idLD = 30
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