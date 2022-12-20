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

class CompanyСard {

    fun new(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        db: Maindb,
        actionType:String?
    ) {
        ForDoc().visible(activity, view, 1, 16)
        bind.apply {
            txtHeader.text = "Карточка предприятия"
            txtInfo1.text = "ОКТМО:"
            txtInfo2.text = "ОКОГУ:"
            txtInfo3.text = "ОКАТО:"
            txtInfo4.text = "ОКФС:"
            txtInfo5.text = "ОКОПФ:"
            txtInfo6.text = "ОКВЭД:"
            divLn6.visibility = View.VISIBLE
            txtInfo7.text = "БАНКОВСКИЕ РЕКВИЗИТЫ:"
            edInput7.visibility = View.GONE
            divLn7.visibility = View.VISIBLE
            txtInfo8.text = "Полное наименование банка:"
            txtInfo9.text = "Расчетный счет:"
            txtInfo10.text = "Корреспондентский счет:"
            txtInfo11.text = "БИК:"
            txtInfo12.text = "Директор:"
            txtInfo13.text = "Бухгалтер:"
            txtInfo14.text = "Эл. почта:"
            txtInfo15.text = "Телефон/факс:"
            txtInfo16.text = "Дополнительно:"
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
                            it.fio = bind.edInput8.text.toString()
                            it.ico = R.drawable.fd_taxes_company_card
                            it.idLD = 21
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