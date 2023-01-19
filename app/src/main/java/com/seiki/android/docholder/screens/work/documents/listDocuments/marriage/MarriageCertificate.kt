package com.seiki.android.docholder.screens.work.documents.listDocuments.marriage

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class MarriageCertificate {

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
            txtHeader.text = "Свидетельство о заключении брака"
            txtInfo1.text = "Серия/номер свидетельства:"
            txtInfo2.text = "Дата заключения брака:"
            txtInfo3.text = "Дата акта о заключении брака:"
            txtInfo4.text = "Номер записи акта:"
            divLn4.visibility = View.VISIBLE
            txtInfo5.text = "МУЖ:"
            edInput5.visibility = View.GONE
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "ФИО:"
            txtInfo7.text = "Дата рождения:"
            txtInfo8.text = "Место рождения:"
            divLn8.visibility = View.VISIBLE
            txtInfo9.text = "ЖЕНА"
            edInput9.visibility = View.GONE
            divLn9.visibility = View.VISIBLE
            txtInfo10.text = "ФИО:"
            txtInfo11.text = "Дата рождения:"
            txtInfo12.text = "Место рождения:"
        }

        ForDoc().loadPage(actionType, bind, list, currentDoc)

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                30, R.drawable.fd_marriage_marr, R.drawable.gradient_doc_red, "marriage",
                "")
        }
    }

}