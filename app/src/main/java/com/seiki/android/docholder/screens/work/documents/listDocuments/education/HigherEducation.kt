package com.seiki.android.docholder.screens.work.documents.listDocuments.education

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

class HigherEducation {

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
            txtHeader.text = "Диплом о высшем образовании"
            txtInfo1.text = ""
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Серия и номер:"
            txtInfo7.text = "Регистрационный номер:"
            txtInfo8.text = "Дата выдачи диплома:"
            txtInfo9.text = "Город:"
            divLn9.visibility = View.VISIBLE
            txtInfo10.text = "Учебное заведение:"
            txtInfo11.text = "Вид диплома:"
            txtInfo12.text = "Дата решения ГАК:"
            txtInfo13.text = "Специальность:"
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(50)
            ForDoc().loadPage(actionType, bind, list, currentDoc)
        }

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                24, R.drawable.fd_education_high, R.drawable.gradient_doc_red, "education",
                bind.edInput2.text.toString())
        }
    }

}