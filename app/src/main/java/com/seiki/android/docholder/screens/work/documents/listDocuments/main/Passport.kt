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

class Passport {

    fun new(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        actionType: Int?,
        currentDoc:DocModel
    ) {
        val viewModel = ViewModelProvider(activity)[DocumentViewModel::class.java]
        var list = emptyList<DocModel>()
        viewModel.getAllDocs().observe(activity){list = it}

        ForDoc().visible(activity, view, 1, 11)
        bind.apply {
            txtHeader.text = "Паспорт"
            txtInfo1.text = "Серия и номер:"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Дата выдачи:"
            txtInfo7.text = "Код подразделения:"
            txtInfo8.text = "Кем выдан:"
            divLn8.visibility = View.VISIBLE
            txtInfo9.text = "Адрес регистрации:"
            txtInfo10.text = "Кем зарегестрирован:"
            txtInfo11.text = "Дата регистрации:"
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(50)
            ForDoc().loadPage(actionType, bind, list, currentDoc)
        }

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType,list,currentDoc,viewModel,bind,
                1,R.drawable.fd_docks_passport,R.drawable.gradient_doc_red,"main",
                bind.edInput2.text.toString())

        }
    }
}

