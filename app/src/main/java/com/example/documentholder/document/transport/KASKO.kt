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

class KASKO {

    fun new(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        db: Maindb,
        actionType:String?) {
        ForDoc().visible(activity, view, 1, 33)
        bind.apply {
            txtHeader.text = "КАСКО"
            txtInfo1.text = "Серия и номер:"
            txtInfo2.text = "Срок действия С:"
            txtInfo3.text = "Срок действия ПО:"
            txtInfo4.text = "Дата выдачи полиса:"
            divLn4.visibility = View.VISIBLE
            txtInfo5.text = "СТРАХОВЩИК:"
            edInput5.visibility = View.GONE
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Наименование:"
            txtInfo7.text = "Номер телефона:"
            txtInfo8.text = "Email:"
            divLn8.visibility = View.VISIBLE
            txtInfo9.text = "СТРАХОВАТЕЛЬ:"
            edInput9.visibility = View.GONE
            divLn9.visibility = View.VISIBLE
            txtInfo10.text = "ФИО:"
            txtInfo11.text = "Дата рождения:"
            txtInfo12.text = "Серия/номер документа:"
            txtInfo13.text = "Дата выдачи документа:"
            txtInfo14.text = "Адрес:"
            txtInfo15.text = "ИНН:"
            divLn15.visibility = View.VISIBLE
            txtInfo16.text = "ТРАНСПОРТНОЕ СРЕДСТВО:"
            edInput16.visibility = View.GONE
            divLn16.visibility = View.VISIBLE
            txtInfo17.text = "Марка, модель:"
            txtInfo18.text = "Мощность:"
            txtInfo19.text = "Год выпуска:"
            txtInfo20.text = "номер VIN:"
            txtInfo21.text = "Регистрационный знак:"
            txtInfo22.text = "Серия/номер ПТС:"
            txtInfo23.text = "Число водителей:"
            divLn23.visibility = View.VISIBLE
            txtInfo24.text = "УСЛОВИЯ:"
            edInput24.visibility = View.GONE
            divLn24.visibility = View.VISIBLE
            txtInfo25.text = "Сюрвейерские условия:"
            txtInfo26.text = "Порядок оплаты:"
            txtInfo27.text = "Страховая премия:"
            txtInfo28.text = "Ущерб:"
            txtInfo29.text = "Хищения:"
            txtInfo30.text = "Гражданская ответственность:"
            txtInfo31.text = "Адрес текущего собственника:"
            txtInfo32.text = "Дополнительное оборудование:"
            txtInfo33.text = "Прочее:"
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
                            it.fio = bind.edInput6.text.toString()
                            it.ico = R.drawable.fd_transport_kasko
                            it.idLD = 15
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