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

class OMS {

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

        ForDoc().visible(activity, view, 2, 14)
        bind.apply {
            txtHeader.text = "Полис ОМС"
            txtInfo1.text = ""
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Номер полиса:"
            txtInfo7.text = "Серия и номер бланка:"
            divLn7.visibility = View.VISIBLE
            txtInfo8.text = "СТРАХОВОЕ УЧРЕЖДЕНИЕ"
            edInput8.visibility = View.GONE
            divLn8.visibility = View.VISIBLE
            txtInfo9.text = "Название:"
            txtInfo10.text = "Адрес:"
            txtInfo11.text = "Телефон фонда ОМС:"
            txtInfo12.text = "PIN код:"
            txtInfo13.text = "PUK код:"
            txtInfo14.text = "Cрок действия:"
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(50)
            ForDoc().loadPage(actionType, bind, list, currentDoc)
        }

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                26, R.drawable.fd_medicine_oms, R.drawable.gradient_doc_blue, "medicine",
                bind.edInput2.text.toString())
        }
    }

}