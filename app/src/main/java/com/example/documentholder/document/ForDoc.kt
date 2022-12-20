package com.example.documentholder.document

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.documentholder.DataBase.DocumentDb
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.R
import com.example.documentholder.databinding.FragmentDocumentNewBinding
import com.example.documentholder.misc.DataModel
import com.example.documentholder.misc.Log
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForDoc {

    fun baseSave(it: DocumentDb, bind: FragmentDocumentNewBinding) {
        //it.birth_date = bind.edInput2.text.toString()
        //it.fio = bind.edInput3.text.toString()
        //it.birth_pos = bind.edInput4.text.toString()
        //it.sex = bind.edInput5.text.toString()
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

    fun baseLoad(it: DocumentDb, bind: FragmentDocumentNewBinding) {
        //bind.edInput2.setText(it.birth_date)
        //bind.edInput3.setText(it.fio)
        //bind.edInput4.setText(it.birth_pos)
        //bind.edInput5.setText(it.sex)
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

    }

    //деактивация окон для просмотра
    fun disableText(bind: FragmentDocumentNewBinding, activity: FragmentActivity) {
        bind.apply {
            edInput1.setKeyListener(null)
            edInput2.setKeyListener(null)
            edInput3.setKeyListener(null)
            edInput4.setKeyListener(null)
            edInput5.setKeyListener(null)
            edInput6.setKeyListener(null)
            edInput7.setKeyListener(null)
            edInput8.setKeyListener(null)
            edInput9.setKeyListener(null)
            edInput10.setKeyListener(null)
            edInput11.setKeyListener(null)
            edInput12.setKeyListener(null)
            edInput13.setKeyListener(null)
            edInput14.setKeyListener(null)
            edInput15.setKeyListener(null)
            edInput16.setKeyListener(null)
            edInput17.setKeyListener(null)
            edInput18.setKeyListener(null)
            edInput19.setKeyListener(null)
            edInput20.setKeyListener(null)
            edInput21.setKeyListener(null)
            edInput22.setKeyListener(null)
            edInput23.setKeyListener(null)
            edInput24.setKeyListener(null)
            edInput25.setKeyListener(null)
            edInput26.setKeyListener(null)
            edInput27.setKeyListener(null)
            edInput28.setKeyListener(null)
            edInput29.setKeyListener(null)
            edInput30.setKeyListener(null)
            edInput31.setKeyListener(null)
            edInput32.setKeyListener(null)
            edInput33.setKeyListener(null)
            edInput34.setKeyListener(null)
            edInput35.setKeyListener(null)
            edInput36.setKeyListener(null)
            edInput37.setKeyListener(null)
            edInput38.setKeyListener(null)
            edInput39.setKeyListener(null)
            edInput40.setKeyListener(null)
        }
    }

    //инициалы из ФИО для карточек
    fun capitalize(string: String): String {
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


    //вывод конкретного документа
    fun lifeID(activity: FragmentActivity): Int {
        val dataModel: DataModel by activity.viewModels()
        var idDoc = 0
        dataModel.msgDocumentID.observe(activity as LifecycleOwner) {
            idDoc = it.toString().toInt()
        }
        return idDoc
    }


    //вернутся на страниицу списка всех документов
    fun backDock(activity: FragmentActivity) {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.docFragmentHolder, DocumentFragment.newInstance())
            .commit()
    }


    //видимость нужных полей для документа
    fun visible(activity: FragmentActivity, view: View, startRange: Int, endRange: Int) {
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

    //Предзаполнение основных полей
    fun preloadDoc(db: Maindb, bind: FragmentDocumentNewBinding) {
        scopeDef.launch {
            db.getDao().getDoc().forEach {
                withContext(Dispatchers.Main) {
                    bind.edInput2.setText(it.fio)
                    bind.edInput3.setText(it.birth_date)
                    bind.edInput4.setText(it.birth_pos)
                    bind.edInput5.setText(it.sex)
                }
            }
        }
    }

}