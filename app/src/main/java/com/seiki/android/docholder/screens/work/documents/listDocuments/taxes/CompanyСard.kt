package com.seiki.android.docholder.screens.work.documents.listDocuments.taxes

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class CompanyСard {

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

        ForDoc().visible(activity, view, 1, 16)
        bind.apply {
            txtHeader.text = "Карточка предприятия"
            txtInfo1.text = "ОКТМО:"
            txtInfo2.text = "ОКОГУ:"
            txtInfo3.text = "ОКАТО:"
            txtInfo4.text = "ОКФС:"
            txtInfo5.text = "ОКОПФ:"
            txtInfo6.text = "ОКВЭД:"
            divLn6.visibility = View.VISIBLE
            txtInfo7.text = "БАНКОВСКИЕ РЕКВИЗИТЫ:"
            edInput7.visibility = View.GONE
            divLn7.visibility = View.VISIBLE
            txtInfo8.text = "Полное наименование банка:"
            txtInfo9.text = "Расчетный счет:"
            txtInfo10.text = "Корреспондентский счет:"
            txtInfo11.text = "БИК:"
            txtInfo12.text = "Директор:"
            txtInfo13.text = "Бухгалтер:"
            txtInfo14.text = "Эл. почта:"
            txtInfo15.text = "Телефон/факс:"
            txtInfo16.text = "Дополнительно:"
        }

        ForDoc().loadPage(actionType, bind, list, currentDoc)

        bind.imgSaveBtn.setOnClickListener {
            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                21, R.drawable.fd_taxes_company_card, R.drawable.gradient_doc_green, "taxes",
                bind.edInput8.text.toString())
        }
    }

}
