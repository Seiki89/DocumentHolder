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

class VZR {

    fun new(activity: FragmentActivity, bind: FragmentDocumentNewBinding, view: View, db: Maindb,
            actionType:String?) {
        ForDoc().visible(activity, view, 1,18)
        bind.apply {
            txtHeader.text = "Страховка ВЗР"
            txtInfo1.text = "ФИО(латиницей):"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Номер полиса:"
            txtInfo7.text = "Территория страхования:"
            txtInfo8.text = "Срок действия С:"
            txtInfo9.text = "Срок действия ПО:"
            divLn9.visibility = View.VISIBLE
            txtInfo10.text = "СТРАХОВЩИК"
            edInput10.visibility = View.GONE
            divLn8.visibility =View.VISIBLE
            txtInfo11.text = "Наименование компании:"
            txtInfo12.text = "Номер телефона:"
            txtInfo13.text = "Адрес:"
            txtInfo14.text = "Страховая сумма:"
            divLn14.visibility = View.VISIBLE
            txtInfo15.text = "СЕРВИСНАЯ КОМПАНИЯ"
            edInput15.visibility = View.GONE
            divLn15.visibility =View.VISIBLE
            txtInfo16.text = "Наименование компании:"
            txtInfo17.text = "Номер телефона:"
            txtInfo18.text = "Другие контакты:"
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
                            it.ico = R.drawable.fd_medicine_vzr
                            it.idLD = 28
                            it.bgDoc = R.drawable.gradient_doc_grey
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