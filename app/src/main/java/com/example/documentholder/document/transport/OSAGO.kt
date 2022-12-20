package com.example.documentholder.document.transport

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

class OSAGO {

    fun new(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        db: Maindb,
        actionType:String?) {
        ForDoc().visible(activity, view, 1, 13)
        bind.apply {
            txtHeader.text = "ОСАГО"
            txtInfo1.text = "Серия и номер:"
            txtInfo2.text = "Срок страхования С:"
            txtInfo3.text = "Срок страхования ПО:"
            txtInfo4.text = "Дата выдачи полиса:"
            divLn4.visibility = View.VISIBLE
            txtInfo5.text = "Наименование страховой компани:"
            txtInfo6.text = "Адрес страховой компани:"
            txtInfo7.text = "Номер телефона страховой компани:"
            divLn7.visibility = View.VISIBLE
            txtInfo8.text = "ФИО страхователя:"
            divLn7.visibility = View.VISIBLE
            txtInfo9.text = "Регистрационный знак ТС:"
            txtInfo10.text = "Марка и модель ТС:"
            txtInfo11.text = "VIN но мер кузова:"
            txtInfo12.text = "Серия и номер паспорта ТС:"
            txtInfo13.text = "Страховая премия:"
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
                            it.type = "transport"
                            ForDoc().baseSave(it, bind)
                            it.fio = bind.edInput8.text.toString()
                            it.ico = R.drawable.fd_transport_osago
                            it.idLD = 14
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