package com.seiki.android.docholder.screens.work.documents.listDocuments.transport

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class STS {

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

        ForDoc().loadPage(actionType, bind, list, currentDoc)

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                12, R.drawable.fd_transport_sts, R.drawable.gradient_doc_red, "transport",
                bind.edInput5.text.toString())
        }
    }

}
