package com.seiki.android.docholder.screens.work.documents.listDocuments.taxes

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.PhotoViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class INNIP {

    fun new(
        activity: FragmentActivity,
        bind: FragmentDocumentNewBinding,
        view: View,
        actionType: Int?,
        currentDoc: DocModel,
        photodata: PhotoViewModel
    ) {

        val viewModel = ViewModelProvider(activity)[DocumentViewModel::class.java]
        var list = emptyList<DocModel>()
        viewModel.getAllDocs().observe(activity) { list = it }

        ForDoc().visible(activity, view, 1, 7)
        bind.apply {
            txtHeader.text = "ИНН(юр.)"
            txtInfo1.text = "Серия, номер:"
            txtInfo2.text = "Наименование юр. лица:"
            txtInfo3.text = "ОГРН:"
            txtInfo4.text = "Дата выдачи:"
            txtInfo5.text = "Орган выдавший документ:"
            txtInfo6.text = "ИНН/КПП:"
            txtInfo7.text = "Код налогового органа:"
        }

        ForDoc().loadPage(actionType, bind, list, currentDoc,photodata)

        bind.imgSaveBtn.setOnClickListener {
            var photo1 = ""
            var photo2 = ""
            var photo3 = ""
            var photo4 = ""

            photodata.messagePhoto1.observe(activity as LifecycleOwner) { photo1 = it }
            photodata.messagePhoto2.observe(activity as LifecycleOwner) { photo2 = it }
            photodata.messagePhoto3.observe(activity as LifecycleOwner) { photo3 = it }
            photodata.messagePhoto4.observe(activity as LifecycleOwner) { photo4 = it }

            ForDoc().clickSave(actionType, list, currentDoc, viewModel, bind,
                17, R.drawable.fd_taxes_inn, R.drawable.gradient_doc_yellow, "taxes",
                bind.edInput2.text.toString(),photo1,photo2,photo3,photo4)
        }
    }

}