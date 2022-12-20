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

class STS {

    fun new(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        db: Maindb,
        actionType:String?) {
        ForDoc().visible(activity, view, 1, 25)
        bind.apply {
            txtHeader.text = "СТС"
            txtInfo1.text = "Серия и номер:"
            divLn1.visibility = View.VISIBLE
            txtInfo2.text = "Регистрационный знак:"
            txtInfo3.text = "Идентифекацмонный номер (VIN):"
            txtInfo4.text = "Марка:"
            txtInfo5.text = "Модель:"
            txtInfo6.text = "Тип ТС:"
            txtInfo7.text = "Категория ТС:"
            txtInfo8.text = "Год выпуска ТС:"
            txtInfo9.text = "Модель двигателя:"
            txtInfo10.text = "Номер двигателя:"
            txtInfo11.text = "Номер шасси:"
            txtInfo12.text = "Номер кузова:"
            txtInfo13.text = "Цвет:"
            txtInfo14.text = "Мощность двигателя:"
            txtInfo15.text = "Рабочий обьем двигателя:"
            txtInfo16.text = "Серия и номер ПТС:"
            txtInfo17.text = "Разрешенная максимальная масса:"
            txtInfo18.text = "Масса без нагрузки:"
            divLn18.visibility = View.VISIBLE
            txtInfo19.text = "ВЛАДЕЛЕЦ:"
            edInput19.visibility = View.GONE
            divLn19.visibility = View.VISIBLE
            txtInfo20.text = "Имя:"
            txtInfo21.text = "Адрес:"
            txtInfo22.text = "Ососбые отметки:"
            divLn22.visibility = View.VISIBLE
            txtInfo23.text = "Код подразделения ГИБДД:"
            txtInfo24.text = "Дата выдачи:"
            txtInfo25.text = "Выдано ГИБДД:"
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
                            it.fio = bind.edInput5.text.toString()
                            it.ico = R.drawable.fd_transport_sts
                            it.idLD = 12
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