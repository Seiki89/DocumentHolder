package com.seiki.android.docholder.screens.work.documents.listDocuments.taxes

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class BankingDetails {

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

        ForDoc().visible(activity, view, 1, 7)
        bind.apply {
            txtHeader.text = "Реквезиты счета"
            txtInfo1.text = "БИК банка получателя:"
            txtInfo2.text = "Наименование банка получателя:"
            txtInfo3.text = "Корсчет банка получателя:"
            txtInfo4.text = "ФИО получателя/Название юр.лица:"
            txtInfo5.text = "ИНН получателя:"
            txtInfo6.text = "КПП получателя:"
            txtInfo7.text = "Номер расчетного счета:"
        }

        ForDoc().loadPage(actionType, bind, list, currentDoc)

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                22, R.drawable.fd_taxes_bank_detail, R.drawable.gradient_doc_green, "taxes",
                bind.edInput4.text.toString())
        }
    }

}