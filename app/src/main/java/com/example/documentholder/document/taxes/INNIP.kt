package com.example.documentholder.document.taxes

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

class INNIP {

    fun new(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        db: Maindb,
        actionType:String?
    ) {
        ForDoc().visible(activity, view, 1, 7)
        bind.apply {
            txtHeader.text = "ИНН(юр.)"
            txtInfo1.text = "Серия, номер:"
            txtInfo2.text = "Наименование юр. лица:"
            txtInfo3.text = "ОГРН:"
            txtInfo4.text = "Дата выдачи:"
            txtInfo5.text = "Орган выдавший документ:"
            txtInfo6.text = "ИНН/КПП:"
            txtInfo7.text = "Код налогового органа:"
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
                            it.type = "taxes"
                            ForDoc().baseSave(it, bind)
                            it.fio = bind.edInput2.text.toString()
                            it.ico = R.drawable.fd_taxes_inn
                            it.idLD = 17
                            it.bgDoc = R.drawable.gradient_doc_yellow
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