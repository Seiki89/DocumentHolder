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

class MedicalCard {

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

        ForDoc().visible(activity, view, 2, 13)
        bind.apply {
            txtHeader.text = "Медицинская карта"
            txtInfo1.text = ""
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            txtInfo6.text = "Рост(см.):"
            txtInfo7.text = "Вес(кг.):"
            divLn7.visibility = View.VISIBLE
            txtInfo8.text = "Группа крови:"
            txtInfo9.text = "Заболевания:"
            txtInfo10.text = "Медикаменты:"
            txtInfo11.text = "Аллергии(противопоказания):"
            txtInfo12.text = "Перенесенные операции:"
            txtInfo13.text = "Заметки:"
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(50)
            ForDoc().loadPage(actionType, bind, list, currentDoc)
        }

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                25, R.drawable.fd_medicine_med_card, R.drawable.gradient_doc_red, "medicine",
                bind.edInput2.text.toString())
        }
    }

}