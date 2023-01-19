package com.seiki.android.docholder.screens.work.documents.listDocuments.medicine

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

class DMS {

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

        ForDoc().visible(activity, view, 1, 12)
        bind.apply {
            txtHeader.text = "Полис ДМС"
            txtInfo1.text = "Номер:"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Дата регистрации:"
            txtInfo7.text = "Действителен ДО:"
            divLn7.visibility = View.VISIBLE
            txtInfo8.text = "СТРАХОВОЕ УЧРЕЖДЕНИЕ"
            edInput8.visibility = View.GONE
            divLn8.visibility = View.VISIBLE
            txtInfo9.text = "Название:"
            txtInfo10.text = "Адрес:"
            txtInfo11.text = "Телефон:"
            txtInfo12.text = "Программа страхования:"
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(50)
            ForDoc().loadPage(actionType, bind, list, currentDoc)
        }

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                29, R.drawable.fd_medicine_dms, R.drawable.gradient_doc_green, "medicine",
                bind.edInput2.text.toString())
        }
    }
}