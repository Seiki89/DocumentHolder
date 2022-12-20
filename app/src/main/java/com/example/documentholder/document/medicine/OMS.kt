package com.example.documentholder.document.medicine

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

class OMS {

    fun new(activity: FragmentActivity, bind: FragmentDocumentNewBinding, view: View, db: Maindb,
            actionType:String?) {
        ForDoc().visible(activity, view, 2,14)
        bind.apply {
            txtHeader.text = "Полис ОМС"
            txtInfo1.text = ""
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Номер полиса:"
            txtInfo7.text = "Серия и номер бланка:"
            divLn7.visibility = View.VISIBLE
            txtInfo8.text = "СТРАХОВОЕ УЧРЕЖДЕНИЕ"
            edInput8.visibility = View.GONE
            divLn8.visibility = View.VISIBLE
            txtInfo9.text = "Название:"
            txtInfo10.text = "Адрес:"
            txtInfo11.text = "Телефон фонда ОМС:"
            txtInfo12.text = "PIN код:"
            txtInfo13.text = "PUK код:"
            txtInfo14.text = "Cрок действия:"
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
                            it.type = "medicine"
                            ForDoc().baseSave(it, bind)
                            it.fio = bind.edInput2.text.toString()
                            it.ico = R.drawable.fd_medicine_oms
                            it.idLD = 26
                            it.bgDoc = R.drawable.gradient_doc_blue
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