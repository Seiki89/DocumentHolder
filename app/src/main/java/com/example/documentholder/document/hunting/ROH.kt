package com.example.documentholder.document.hunting

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

class ROH {

    fun new(activity: FragmentActivity, bind: FragmentDocumentNewBinding, view: View, db: Maindb,
    actionType:String?) {
        ForDoc().visible(activity, view, 1,13)
        bind.apply {
            txtHeader.text = "Разрешение РОХа"
            txtInfo1.text = "Номер свидетельства"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место регистрации:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Место регистрации:"
            txtInfo6.text = "Дата выдачи:"
            txtInfo7.text = "Кем выдан:"
            txtInfo8.text = "Действителен до:"
            divLn8.visibility = View.VISIBLE
            txtInfo9.text = "ОРУЖИЕ"
            edInput9.visibility = View.GONE
            divLn9.visibility = View.VISIBLE
            txtInfo10.text = "Серия/номер:"
            txtInfo11.text = "Модель:"
            txtInfo12.text = "Калибр:"
            txtInfo13.text = "Год выпуска:"
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
                            it.type = "hunting"
                            ForDoc().baseSave(it, bind)
                            it.fio = bind.edInput2.text.toString()
                            it.ico = R.drawable.fd_hunting_roh
                            it.idLD = 34
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