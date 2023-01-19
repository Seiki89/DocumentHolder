package com.seiki.android.docholder.screens.work.documents.listDocuments.main

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InternationalPassport {

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

        ForDoc().visible(activity, view, 1, 9)
        bind.apply {
            txtHeader.text = "Загранпаспорт"
            txtInfo1.text = "Серия и номер:"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            txtInfo6.text = "Гражданство:"
            divLn6.visibility = View.VISIBLE
            txtInfo7.text = "Кем выдан:"
            txtInfo8.text = "Дата выдачи:"
            txtInfo9.text = "Дата окончания срока действия:"
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(50)
            ForDoc().loadPage(actionType, bind, list, currentDoc)
        }

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                5, R.drawable.fd_docks_international_pas, R.drawable.gradient_doc_red, "main",
                bind.edInput2.text.toString())
        }
    }
}