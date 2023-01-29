package com.seiki.android.docholder.screens.work.documents.listDocuments.transport

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

class DriverLicense {
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

        ForDoc().visible(activity, view, 1, 15)
        bind.apply {
            txtHeader.text = "Водительское удостоверение"
            txtInfo1.text = "Серия и номер:"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            txtInfo6.text = "Дата выдачи:"
            txtInfo7.text = "Место рождения (Латиницей):"
            divLn7.visibility = View.VISIBLE
            txtInfo8.text = "Кем выдано:"
            txtInfo9.text = "Кем выдано (Латиницей):"
            txtInfo10.text = "Место жительства:"
            txtInfo11.text = "Место жительства (Латиницей):"
            txtInfo12.text = "Категории управления ТС:"
            txtInfo13.text = "Дата выдачи:"
            txtInfo14.text = "Действует до:"
            txtInfo15.text = "Ососбые отметки:"
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
                11, R.drawable.fd_transport_driver_lic, R.drawable.gradient_doc_red, "transport",
                bind.edInput2.text.toString(),photo1,photo2,photo3,photo4)
        }
    }

}