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

class PTS {

    fun new(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        db: Maindb,
        actionType:String?) {
        ForDoc().visible(activity, view, 1, 37)
        bind.apply {
            txtHeader.text = "ПТС"
            txtInfo1.text = "Серия и номер:"
            txtInfo2.text = "Идентифекацмонный номер (VIN):"
            txtInfo3.text = "Марка,модель ТС:"
            txtInfo4.text = "Тип ТС:"
            txtInfo5.text = "Категория ТС:"
            txtInfo6.text = "Год выпуска ТС:"
            txtInfo7.text = "Модель, № двигателя:"
            txtInfo8.text = "Номер шасси:"
            txtInfo9.text = "Цвет кузова:"
            txtInfo10.text = "Номер кузова:"
            txtInfo11.text = "Мощность двигателя:"
            txtInfo12.text = "Рабочий обьем двигателя:"
            txtInfo13.text = "Тип двигателя:"
            txtInfo14.text = "Разрешенная максимальная масса (кг):"
            txtInfo15.text = "Масса без нагрузки (кг):"
            txtInfo16.text = "Страна изготовитель ТС"
            txtInfo17.text = "Одобрение типа ТС №"
            txtInfo18.text = "Дата одобрения:"
            txtInfo19.text = "Кем выдано:"
            txtInfo20.text = "Страна ввоза ТС:"
            txtInfo21.text = "Серия и номер ГТД:"
            txtInfo22.text = "Таможенные ограничения:"
            divLn22.visibility = View.VISIBLE
            txtInfo23.text = "Наименование/ФИО собстаенника:"
            txtInfo24.text = "Адрес собственика:"
            txtInfo25.text = "Наименование организации выдавшей паспорт:"
            txtInfo26.text = "Адрес выдачи паспорта"
            txtInfo27.text = "Дата выдачи паспорта"
            divLn27.visibility = View.VISIBLE
            txtInfo28.text = "Особые отметки:"
            divLn28.visibility = View.VISIBLE
            txtInfo29.text = "ФИО текущего собственника:"
            txtInfo30.text = "Адрес текущего собственника:"
            txtInfo31.text = "Дата продажи (передачи):"
            txtInfo32.text = "Документ на право собственности:"
            txtInfo33.text = "Документ о регистрации ТС:"
            txtInfo34.text = "Серия и номер:"
            txtInfo35.text = "Регистрационный знак:"
            txtInfo36.text = "Дата регистрации:"
            txtInfo37.text = "Выдано ГИБДД:"
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
                            it.fio = bind.edInput3.text.toString()
                            it.ico = R.drawable.fd_transport_pts
                            it.idLD = 13
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