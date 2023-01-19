package com.seiki.android.docholder.screens.work.documents.listDocuments.hunting

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

class HuntingTicket {

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
            txtHeader.text = "Охотничий билет"
            txtInfo1.text = "Номер"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место регистрации:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Дата выдачи:"
            txtInfo7.text = "Кем выдан:"
            txtInfo8.text = "Действителен до:"
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(50)
            ForDoc().loadPage(actionType, bind, list, currentDoc)
        }

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                33, R.drawable.fd_hunting_hunting_tick, R.drawable.gradient_doc_green, "hunting",
                bind.edInput2.text.toString())
        }
    }
}