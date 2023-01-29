package com.seiki.android.docholder.screens.work.documents.listDocuments.main

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MilitaryTicket {

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

        ForDoc().visible(activity, view, 1, 20)
        bind.apply {
            txtHeader.text = "Военный билет"
            txtInfo1.text = "Серия и номер:"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Дата выдачи:"
            txtInfo7.text = "Место выдачи:"
            divLn7.visibility = View.VISIBLE
            txtInfo8.text = "Номер ВУС:"
            txtInfo9.text = "Наименование ВУС:"
            txtInfo10.text = "Код ВУС:"
            divLn10.visibility = View.VISIBLE
            txtInfo11.text = "Воинское звание:"
            txtInfo12.text = "Кем присвоено:"
            txtInfo13.text = "Наименование призывной комиссии:"
            txtInfo14.text = "Номер приказа:"
            txtInfo15.text = "Дата приказа:"
            txtInfo16.text = "Категория запаса:"
            txtInfo17.text = "Группа учета:"
            txtInfo18.text = "Состав:"
            txtInfo19.text = "Категория годности:"
            txtInfo20.text = "Комиссариат:"
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(50)
            ForDoc().loadPage(actionType, bind, list, currentDoc,photodata)
        }

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
                4, R.drawable.fd_docks_military_tick, R.drawable.gradient_doc_red, "main",
                bind.edInput2.text.toString(),photo1,photo2,photo3,photo4)
        }
    }
}