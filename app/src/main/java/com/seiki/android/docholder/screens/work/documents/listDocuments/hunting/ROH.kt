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

class ROH {

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
            txtHeader.text = "Разрешение РОХа"
            txtInfo1.text = "Номер свидетельства"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место регистрации:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Место регистрации:"
            txtInfo6.text = "Дата выдачи:"
            txtInfo7.text = "Кем выдан:"
            txtInfo8.text = "Действителен до:"
            divLn8.visibility = View.VISIBLE
            txtInfo9.text = "ОРУЖИЕ"
            edInput9.visibility = View.GONE
            divLn9.visibility = View.VISIBLE
            txtInfo10.text = "Серия/номер:"
            txtInfo11.text = "Модель:"
            txtInfo12.text = "Калибр:"
            txtInfo13.text = "Год выпуска:"
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(50)
            ForDoc().loadPage(actionType, bind, list, currentDoc)
        }

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                34, R.drawable.fd_hunting_roh, R.drawable.gradient_doc_blue, "hunting",
                bind.edInput2.text.toString())
        }
    }
}