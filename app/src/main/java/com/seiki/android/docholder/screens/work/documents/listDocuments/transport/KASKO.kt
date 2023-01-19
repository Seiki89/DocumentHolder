package com.seiki.android.docholder.screens.work.documents.listDocuments.transport

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class KASKO {

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

        ForDoc().loadPage(actionType, bind, list, currentDoc)

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                15, R.drawable.fd_transport_kasko, R.drawable.gradient_doc_yellow, "transport",
                bind.edInput6.text.toString())
        }
    }

}
