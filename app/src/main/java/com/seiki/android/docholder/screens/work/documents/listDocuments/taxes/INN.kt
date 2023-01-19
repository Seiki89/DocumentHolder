package com.seiki.android.docholder.screens.work.documents.listDocuments.taxes

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class INN {

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

        ForDoc().visible(activity, view, 1, 6)
        bind.apply {
            txtHeader.text = "ИНН"
            txtInfo1.text = "Номер ИНН:"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            divLn3.visibility = View.VISIBLE
            txtInfo4.text = "Серия и номер:"
            txtInfo5.text = "Орган выдавший документ:"
            txtInfo6.text = "Дата выдачи:"
        }

        ForDoc().loadPage(actionType, bind, list, currentDoc)

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                16, R.drawable.fd_taxes_inn, R.drawable.gradient_doc_yellow, "taxes",
                bind.edInput2.text.toString())
        }
    }

}