package com.seiki.android.docholder.screens.work.documents.listDocuments.marriage

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

class CertificateOfDivorce {

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

        ForDoc().visible(activity, view, 1, 12)
        bind.apply {
            txtHeader.text = "Свидетельство о расторжении брака"
            txtInfo1.text = "Серия/номер свидетельства:"
            txtInfo2.text = "Дата Расторжения брака:"
            txtInfo3.text = "Дата акта о расторжении брака:"
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
                31, R.drawable.fd_marriage_dev, R.drawable.gradient_doc_grey, "marriage",
                "",photo1,photo2,photo3,photo4)
        }
    }
}