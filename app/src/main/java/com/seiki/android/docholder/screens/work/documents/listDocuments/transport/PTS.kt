package com.seiki.android.docholder.screens.work.documents.listDocuments.transport

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class PTS {

    fun new(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        actionType: Int?,
        currentDoc: DocModel,
    ) {

        val viewModel = ViewModelProvider(activity)[DocumentViewModel::class.java]
        var list = emptyList<DocModel>()
        viewModel.getAllDocs().observe(activity) { list = it }

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

        ForDoc().loadPage(actionType, bind, list, currentDoc)

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                13, R.drawable.fd_transport_pts, R.drawable.gradient_doc_green, "transport",
                bind.edInput3.text.toString())
        }
    }

}
