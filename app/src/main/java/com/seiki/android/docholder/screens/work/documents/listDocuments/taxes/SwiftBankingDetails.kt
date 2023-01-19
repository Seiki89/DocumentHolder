package com.seiki.android.docholder.screens.work.documents.listDocuments.taxes

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class SwiftBankingDetails {

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

        ForDoc().visible(activity, view, 1, 8)
        bind.apply {
            txtHeader.text = "Реквизиты swift"
            txtInfo1.text = "SWIFT банка получателя:"
            txtInfo2.text = "Наименование банка получателя:"
            txtInfo3.text = "Адрес банка получателя:"
            txtInfo4.text = "Получатель:"
            txtInfo5.text = "Номер счета получателя:"
            txtInfo6.text = "SWIFT банка-корреспондента:"
            txtInfo7.text = "Название банка-корреспондента (Intermediary):"
            txtInfo8.text = "Счет в банке-корреспонденте(Benefit Bank Account):"
        }

        ForDoc().loadPage(actionType, bind, list, currentDoc)

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                20, R.drawable.fd_taxes_swift, R.drawable.gradient_doc_blue, "taxes",
                bind.edInput2.text.toString())
        }
    }

}
