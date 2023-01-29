package com.seiki.android.docholder.screens.work.documents.doc_new_fragment

import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.PhotoViewModel


class ForDoc {

    val zeroBaseInit = DocModel(
        0, "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "",
        "", "", "", "", 0, 0, 0,
        "","","","")

    fun loadPage(actionType:Int?,bind:FragmentDocumentNewBinding,list: List<DocModel>, currentDoc:DocModel,photodata:PhotoViewModel){
        // действия при подгрузке страницы конкретного документа
        // 11 - новый 12 - изменить 13 - открыть
        if (actionType == 11) { ForDoc().preloadDoc(list, bind) }
        if (actionType == 12) { bind.imgBackCircle.setImageResource(R.drawable.ic_back_centr) }
        if (actionType == 12 || actionType == 13) {
            ForDoc().baseLoad(currentDoc, bind, photodata)
        }
        if (actionType == 13) {
            bind.imgBtnCircle.visibility = View.GONE
            bind.imgSaveIco.setImageResource(R.drawable.ic_back_centr)
            ForDoc().disableText(bind)
        }
    }

    fun clickSave(
        actionType:Int?,list: List<DocModel>,currentDoc:DocModel,
        viewModel:DocumentViewModel, bind:FragmentDocumentNewBinding,
        idLDNum:Int,ico:Int,color:Int,typeText:String,fioText:String,
        photo1:String,photo2:String,photo3:String,photo4:String){
        // сохранение документа при нажатии на кнопку (нового\внесение изменений)
        if (actionType == 11) {
            list.forEach {
                if (it.id == 0) {
                    it.id = null
                    it.type = typeText
                    ForDoc().baseSave(it, bind)
                    it.photo1 = photo1
                    it.photo2 = photo2
                    it.photo3 = photo3
                    it.photo4 = photo4
                    it.fio = fioText
                    it.ico = ico
                    it.idLD = idLDNum
                    it.bgDoc = color
                    viewModel.insert(it)
                }
            }
        }
        if (actionType == 12) {
            list.forEach {
                if (it.id == currentDoc.id) {
                    ForDoc().baseSave(it, bind)
                    it.photo1 = photo1
                    it.photo2 = photo2
                    it.photo3 = photo3
                    it.photo4 = photo4
                    it.fio = fioText
                    viewModel.insert(it)
                }
            }
        }
        ForDoc().backDock()
    }

    fun baseSave(it: DocModel, bind: FragmentDocumentNewBinding) {
        //сохранить в базу
        it.NameDoc = bind.txtHeader.text.toString()
        it.a1 = bind.edInput1.text.toString()
        it.a2 = bind.edInput2.text.toString()
        it.a3 = bind.edInput3.text.toString()
        it.a4 = bind.edInput4.text.toString()
        it.a5 = bind.edInput5.text.toString()
        it.a6 = bind.edInput6.text.toString()
        it.a7 = bind.edInput7.text.toString()
        it.a8 = bind.edInput8.text.toString()
        it.a9 = bind.edInput9.text.toString()
        it.a10 = bind.edInput10.text.toString()
        it.a11 = bind.edInput11.text.toString()
        it.a12 = bind.edInput12.text.toString()
        it.a13 = bind.edInput13.text.toString()
        it.a14 = bind.edInput14.text.toString()
        it.a15 = bind.edInput15.text.toString()
        it.a16 = bind.edInput16.text.toString()
        it.a17 = bind.edInput17.text.toString()
        it.a18 = bind.edInput18.text.toString()
        it.a19 = bind.edInput19.text.toString()
        it.a20 = bind.edInput20.text.toString()
        it.a21 = bind.edInput21.text.toString()
        it.a22 = bind.edInput22.text.toString()
        it.a23 = bind.edInput23.text.toString()
        it.a24 = bind.edInput24.text.toString()
        it.a25 = bind.edInput25.text.toString()
        it.a26 = bind.edInput26.text.toString()
        it.a27 = bind.edInput27.text.toString()
        it.a28 = bind.edInput28.text.toString()
        it.a29 = bind.edInput29.text.toString()
        it.a30 = bind.edInput30.text.toString()
        it.a31 = bind.edInput31.text.toString()
        it.a32 = bind.edInput32.text.toString()
        it.a33 = bind.edInput33.text.toString()
        it.a34 = bind.edInput34.text.toString()
        it.a35 = bind.edInput35.text.toString()
        it.a36 = bind.edInput36.text.toString()
        it.a37 = bind.edInput37.text.toString()
        it.a38 = bind.edInput38.text.toString()
        it.a39 = bind.edInput39.text.toString()
        it.a40 = bind.edInput40.text.toString()
    }

    fun baseLoad(it: DocModel, bind: FragmentDocumentNewBinding,photodata:PhotoViewModel) {
        //загрузить из базы
        bind.txtHeader.text = it.NameDoc
        bind.edInput1.setText(it.a1)
        bind.edInput2.setText(it.a2)
        bind.edInput3.setText(it.a3)
        bind.edInput4.setText(it.a4)
        bind.edInput5.setText(it.a5)
        bind.edInput6.setText(it.a6)
        bind.edInput7.setText(it.a7)
        bind.edInput8.setText(it.a8)
        bind.edInput9.setText(it.a9)
        bind.edInput10.setText(it.a10)
        bind.edInput11.setText(it.a11)
        bind.edInput12.setText(it.a12)
        bind.edInput13.setText(it.a13)
        bind.edInput14.setText(it.a14)
        bind.edInput15.setText(it.a15)
        bind.edInput16.setText(it.a16)
        bind.edInput17.setText(it.a17)
        bind.edInput18.setText(it.a18)
        bind.edInput19.setText(it.a19)
        bind.edInput20.setText(it.a20)
        bind.edInput21.setText(it.a21)
        bind.edInput22.setText(it.a22)
        bind.edInput23.setText(it.a23)
        bind.edInput24.setText(it.a24)
        bind.edInput25.setText(it.a25)
        bind.edInput26.setText(it.a26)
        bind.edInput27.setText(it.a27)
        bind.edInput28.setText(it.a28)
        bind.edInput29.setText(it.a29)
        bind.edInput30.setText(it.a30)
        bind.edInput31.setText(it.a31)
        bind.edInput32.setText(it.a32)
        bind.edInput33.setText(it.a33)
        bind.edInput34.setText(it.a34)
        bind.edInput35.setText(it.a35)
        bind.edInput36.setText(it.a36)
        bind.edInput37.setText(it.a37)
        bind.edInput38.setText(it.a38)
        bind.edInput39.setText(it.a39)
        bind.edInput40.setText(it.a40)
        if (it.photo1.isNotBlank()){
            bind.photo1doc.setImageURI(Uri.parse(it.photo1))
            photodata.messagePhoto1.value = it.photo1
        }
        if (it.photo2.isNotBlank()){
            bind.photo2doc.setImageURI(Uri.parse(it.photo2))
            photodata.messagePhoto2.value = it.photo2
        }
        if (it.photo3.isNotBlank()){
            bind.photo3doc.setImageURI(Uri.parse(it.photo3))
            photodata.messagePhoto3.value = it.photo3
        }
        if (it.photo4.isNotBlank()){
            bind.photo4doc.setImageURI(Uri.parse(it.photo4))
            photodata.messagePhoto4.value = it.photo4
        }

    }


    fun disableText(bind: FragmentDocumentNewBinding) {
        //деактивация окон, для просмотра
        bind.apply {
            edInput1.keyListener = null
            edInput2.keyListener = null
            edInput3.keyListener = null
            edInput4.keyListener = null
            edInput5.keyListener = null
            edInput6.keyListener = null
            edInput7.keyListener = null
            edInput8.keyListener = null
            edInput9.keyListener = null
            edInput10.keyListener = null
            edInput11.keyListener = null
            edInput12.keyListener = null
            edInput13.keyListener = null
            edInput14.keyListener = null
            edInput15.keyListener = null
            edInput16.keyListener = null
            edInput17.keyListener = null
            edInput18.keyListener = null
            edInput19.keyListener = null
            edInput20.keyListener = null
            edInput21.keyListener = null
            edInput22.keyListener = null
            edInput23.keyListener = null
            edInput24.keyListener = null
            edInput25.keyListener = null
            edInput26.keyListener = null
            edInput27.keyListener = null
            edInput28.keyListener = null
            edInput29.keyListener = null
            edInput30.keyListener = null
            edInput31.keyListener = null
            edInput32.keyListener = null
            edInput33.keyListener = null
            edInput34.keyListener = null
            edInput35.keyListener = null
            edInput36.keyListener = null
            edInput37.keyListener = null
            edInput38.keyListener = null
            edInput39.keyListener = null
            edInput40.keyListener = null
        }
    }

    fun capitalize(string: String): String {
        //инициалы из ФИО для карточек
        val words = string.split(" ")
        val sb = StringBuilder()
        words.forEach {
            if (it != "") {
                sb.append(it[0])
            }
            sb.append("")
        }
        return sb.toString().trim { it <= ' ' }
    }


    fun backDock() {
        //вернутся на страниицу списка всех документов
        APP.navController.navigate(R.id.action_documentNewFragment_to_documentFragment)
    }



    fun visible(activity: FragmentActivity, view: View, startRange: Int, endRange: Int) {
        //видимость нужных полей для документа
        for (i in startRange..endRange) {
            val txtInf = "txtInfo$i"
            val edinput = "edInput$i"
            val txtID = activity.resources.getIdentifier(txtInf, "id", activity.packageName)
            val edID = activity.resources.getIdentifier(edinput, "id", activity.packageName)
            val txt: TextView = view.findViewById(txtID)
            val edinp: TextView = view.findViewById(edID)
            txt.visibility = View.VISIBLE
            edinp.visibility = View.VISIBLE

        }
    }


    fun preloadDoc(list:List<DocModel>, bind: FragmentDocumentNewBinding) {
        //Предзаполнение основных полей
        list.forEach {
            bind.edInput2.setText(it.fio)
            bind.edInput3.setText(it.birth_date)
            bind.edInput4.setText(it.birth_pos)
            bind.edInput5.setText(it.sex)
        }
    }

}
