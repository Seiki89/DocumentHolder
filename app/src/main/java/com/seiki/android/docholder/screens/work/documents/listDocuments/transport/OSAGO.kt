package com.seiki.android.docholder.screens.work.documents.listDocuments.transport

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class OSAGO {

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

        ForDoc().loadPage(actionType, bind, list, currentDoc)

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                14, R.drawable.fd_transport_osago, R.drawable.gradient_doc_green, "transport",
                bind.edInput8.text.toString())
        }
    }

}