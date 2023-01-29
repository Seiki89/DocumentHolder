package com.seiki.android.docholder.screens.work.documents.listDocuments.medicine

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

class VZR {

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

        ForDoc().visible(activity, view, 1, 18)
        bind.apply {
            txtHeader.text = "Страховка ВЗР"
            txtInfo1.text = "ФИО(латиницей):"
            txtInfo2.text = "ФИО:"
            txtInfo3.text = "Дата рожденя:"
            txtInfo4.text = "Место рождения:"
            txtInfo5.text = "Пол:"
            divLn5.visibility = View.VISIBLE
            txtInfo6.text = "Номер полиса:"
            txtInfo7.text = "Территория страхования:"
            txtInfo8.text = "Срок действия С:"
            txtInfo9.text = "Срок действия ПО:"
            divLn9.visibility = View.VISIBLE
            txtInfo10.text = "СТРАХОВЩИК"
            edInput10.visibility = View.GONE
            divLn8.visibility =View.VISIBLE
            txtInfo11.text = "Наименование компании:"
            txtInfo12.text = "Номер телефона:"
            txtInfo13.text = "Адрес:"
            txtInfo14.text = "Страховая сумма:"
            divLn14.visibility = View.VISIBLE
            txtInfo15.text = "СЕРВИСНАЯ КОМПАНИЯ"
            edInput15.visibility = View.GONE
            divLn15.visibility =View.VISIBLE
            txtInfo16.text = "Наименование компании:"
            txtInfo17.text = "Номер телефона:"
            txtInfo18.text = "Другие контакты:"
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
                28, R.drawable.fd_medicine_vzr, R.drawable.gradient_doc_grey, "medicine",
                bind.edInput2.text.toString(),photo1,photo2,photo3,photo4)
        }
    }
}