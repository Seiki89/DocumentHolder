package com.seiki.android.docholder.screens.work.documents.listDocuments.main

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class Visa {
    fun new(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        actionType: Int?,
        currentDoc: DocModel,
    ){

        val viewModel = ViewModelProvider(activity)[DocumentViewModel::class.java]
        var list = emptyList<DocModel>()
        viewModel.getAllDocs().observe(activity) { list = it }

        ForDoc().visible(activity, view, 1, 5)
        bind.apply {
            txtHeader.text = "Виза"
            txtInfo1.text = "Номер:"
            txtInfo2.text = "Страна выдавшая визу"
            txtInfo3.text = "Действительна для стран:"
            txtInfo4.text = "Срок действия с:"
            txtInfo5.text = "Срок действия до:"
        }

        ForDoc().loadPage(actionType, bind, list, currentDoc)

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                6, R.drawable.fd_docks_visa, R.drawable.gradient_doc_green, "main",
                bind.edInput2.text.toString())
        }
    }
}