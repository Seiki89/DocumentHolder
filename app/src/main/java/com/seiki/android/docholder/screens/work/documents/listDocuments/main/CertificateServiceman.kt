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

class CertificateServiceman {

    fun new(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        actionType: Int?,
        currentDoc: DocModel
    ) {

        val viewModel = ViewModelProvider(activity)[DocumentViewModel::class.java]
        var list = emptyList<DocModel>()
        viewModel.getAllDocs().observe(activity) { list = it }

        ForDoc().visible(activity, view, 1, 18)
        bind.apply {
            txtHeader.text = "Удостоверение военнослужащего"
            txtInfo1.text = "Номер:"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Проходит службу в:"
            txtInfo7.text = "Наименование воинской части:"
            txtInfo8.text = "Воинское звание:"
            txtInfo9.text = "Название приказа о звании:"
            txtInfo10.text = "Номер приказа о звании:"
            txtInfo11.text = "Дата приказа о звании:"
            txtInfo12.text = "Воинская должность:"
            txtInfo13.text = "Название приказа о должности:"
            txtInfo14.text = "Дата приказа о должности:"
            txtInfo15.text = "Имя, подписавшего приказ:"
            txtInfo16.text = "Звание подписавшего приказ:"
            txtInfo17.text = "В/Д подписавшего приказ:"
            txtInfo18.text = "Дата выдачи удостоверения:"
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(50)
            ForDoc().loadPage(actionType, bind, list, currentDoc)
        }

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                8, R.drawable.fd_docks_servisce_cert, R.drawable.gradient_doc_green, "main",
                bind.edInput2.text.toString())
        }
    }
}