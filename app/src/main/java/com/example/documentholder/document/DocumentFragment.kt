package com.example.documentholder.document

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.documentholder.DataBase.DocumentDb
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.R
import com.example.documentholder.WorkActivity
import com.example.documentholder.card.CardAdapter
import com.example.documentholder.databinding.FragmentDocumentBinding
import com.example.documentholder.misc.Log
import com.example.documentholder.misc.scopeDef
import com.example.documentholder.settings.SettingsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DocumentFragment : Fragment() {
    lateinit var bind: FragmentDocumentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentDocumentBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Maindb.db(requireActivity())
        animationStart()

        //предупреждение первого запуска
        scopeDef.launch{
            if (db.getDao().isEmptyDoc()){
                withContext(Dispatchers.Main){
                    bind.preStat.visibility = View.VISIBLE
                }
            }
        }
        bind.preStat.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.docFragmentHolder, SettingsFragment.newInstance())
                .commit()
        }


        //показать типы, сохраненные в базе
        val typeVis = mutableSetOf<String>()
        typeVisibility(typeVis,db)
//рецайклы
        //рецайкл Личные документы
        val itemViewDoc = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        bind.recDocs.layoutManager = itemViewDoc
        scopeDef.launch {
            val idList = mutableListOf<Int?>()
            val idLDList = mutableListOf<Int>()
            val fioList = mutableListOf<String>()
            val nameDocList = mutableListOf<String>()
            val icoList = mutableListOf<Int>()
            val bgDocList = mutableListOf<Int>()
            db.getDao().getDoc().toList().forEach {
                if (it.type == "main") {
                    idList.add(it.id)
                    fioList.add(it.fio)
                    nameDocList.add(it.NameDoc)
                    icoList.add(it.ico)
                    idLDList.add(it.idLD)
                    bgDocList.add(it.bgDoc)
                }
            }
            withContext(Dispatchers.Main) {
                delay(50)
                bind.recDocs.adapter = DocumentAdapter(idList,idLDList,fioList,nameDocList,icoList,bgDocList, requireActivity())
            }
        }
        //рецайкл Транспорт
        val itemViewTransport = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        bind.recTransport.layoutManager = itemViewTransport
        scopeDef.launch {
            val idList = mutableListOf<Int?>()
            val idLDList = mutableListOf<Int>()
            val fioList = mutableListOf<String>()
            val nameDocList = mutableListOf<String>()
            val icoList = mutableListOf<Int>()
            val bgDocList = mutableListOf<Int>()
            db.getDao().getDoc().toList().forEach {
                if (it.type == "transport") {
                    idList.add(it.id)
                    fioList.add(it.fio)
                    nameDocList.add(it.NameDoc)
                    icoList.add(it.ico)
                    idLDList.add(it.idLD)
                    bgDocList.add(it.bgDoc)
                }
            }
            withContext(Dispatchers.Main) {
                delay(50)
                bind.recTransport.adapter = DocumentAdapter(idList,idLDList,fioList,nameDocList,icoList,bgDocList, requireActivity())
            }
        }
        //рецайкл налоги
        val itemViewTaxes = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        bind.recTaxes.layoutManager = itemViewTaxes
        scopeDef.launch {
            val idList = mutableListOf<Int?>()
            val idLDList = mutableListOf<Int>()
            val fioList = mutableListOf<String>()
            val nameDocList = mutableListOf<String>()
            val icoList = mutableListOf<Int>()
            val bgDocList = mutableListOf<Int>()
            db.getDao().getDoc().toList().forEach {
                if (it.type == "taxes") {
                    idList.add(it.id)
                    fioList.add(it.fio)
                    nameDocList.add(it.NameDoc)
                    icoList.add(it.ico)
                    idLDList.add(it.idLD)
                    bgDocList.add(it.bgDoc)
                }
            }
            withContext(Dispatchers.Main) {
                delay(50)
                bind.recTaxes.adapter = DocumentAdapter(idList,idLDList,fioList,nameDocList,icoList,bgDocList, requireActivity())
            }
        }
        //рецайкл Образование
        val itemViewEducation = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        bind.recEducation.layoutManager = itemViewEducation
        scopeDef.launch {
            val idList = mutableListOf<Int?>()
            val idLDList = mutableListOf<Int>()
            val fioList = mutableListOf<String>()
            val nameDocList = mutableListOf<String>()
            val icoList = mutableListOf<Int>()
            val bgDocList = mutableListOf<Int>()
            db.getDao().getDoc().toList().forEach {
                if (it.type == "education") {
                    idList.add(it.id)
                    fioList.add(it.fio)
                    nameDocList.add(it.NameDoc)
                    icoList.add(it.ico)
                    idLDList.add(it.idLD)
                    bgDocList.add(it.bgDoc)
                }
            }
            withContext(Dispatchers.Main) {
                delay(50)
                bind.recEducation.adapter = DocumentAdapter(idList,idLDList,fioList,nameDocList,icoList,bgDocList, requireActivity())
            }
        }
        //рецайкл Медицина
        val itemViewMedicine = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        bind.recMedicine.layoutManager = itemViewMedicine
        scopeDef.launch {
            val idList = mutableListOf<Int?>()
            val idLDList = mutableListOf<Int>()
            val fioList = mutableListOf<String>()
            val nameDocList = mutableListOf<String>()
            val icoList = mutableListOf<Int>()
            val bgDocList = mutableListOf<Int>()
            db.getDao().getDoc().toList().forEach {
                if (it.type == "medicine") {
                    idList.add(it.id)
                    fioList.add(it.fio)
                    nameDocList.add(it.NameDoc)
                    icoList.add(it.ico)
                    idLDList.add(it.idLD)
                    bgDocList.add(it.bgDoc)
                }
            }
            withContext(Dispatchers.Main) {
                delay(50)
                bind.recMedicine.adapter = DocumentAdapter(idList,idLDList,fioList,nameDocList,icoList,bgDocList, requireActivity())
            }
        }
        //рецайкл Животные
        /*val itemViewMedicine = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        bind.recMedicine.layoutManager = itemViewMedicine
        scopeDef.launch {
            val idList = mutableListOf<Int?>()
            val idLDList = mutableListOf<Int>()
            val fioList = mutableListOf<String>()
            val nameDocList = mutableListOf<String>()
            val icoList = mutableListOf<Int>()
            db.getDao().getDoc().toList().forEach {
                if (it.type == "medicine") {
                    idList.add(it.id)
                    fioList.add(it.fio)
                    nameDocList.add(it.NameDoc)
                    icoList.add(it.ico)
                    idLDList.add(it.idLD)
                }
            }
            withContext(Dispatchers.Main) {
                delay(50)
                bind.recMedicine.adapter = DocumentAdapter(idList,idLDList,fioList,nameDocList,icoList, requireActivity())
            }
        }*/
        //рецайкл Брак
        val itemViewMarriage = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        bind.recMarriage.layoutManager = itemViewMarriage
        scopeDef.launch {
            val idList = mutableListOf<Int?>()
            val idLDList = mutableListOf<Int>()
            val fioList = mutableListOf<String>()
            val nameDocList = mutableListOf<String>()
            val icoList = mutableListOf<Int>()
            val bgDocList = mutableListOf<Int>()
            db.getDao().getDoc().toList().forEach {
                if (it.type == "marriage") {
                    idList.add(it.id)
                    fioList.add(it.fio)
                    nameDocList.add(it.NameDoc)
                    icoList.add(it.ico)
                    idLDList.add(it.idLD)
                    bgDocList.add(it.bgDoc)
                }
            }
            withContext(Dispatchers.Main) {
                delay(50)
                bind.recMarriage.adapter = DocumentAdapter(idList,idLDList,fioList,nameDocList,icoList,bgDocList, requireActivity())
            }
        }
        //рецайкл Охота
        val itemViewHunting = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        bind.recHunting.layoutManager = itemViewHunting
        scopeDef.launch {
            val idList = mutableListOf<Int?>()
            val idLDList = mutableListOf<Int>()
            val fioList = mutableListOf<String>()
            val nameDocList = mutableListOf<String>()
            val icoList = mutableListOf<Int>()
            val bgDocList = mutableListOf<Int>()
            db.getDao().getDoc().toList().forEach {
                if (it.type == "hunting") {
                    idList.add(it.id)
                    fioList.add(it.fio)
                    nameDocList.add(it.NameDoc)
                    icoList.add(it.ico)
                    idLDList.add(it.idLD)
                    bgDocList.add(it.bgDoc)
                }
            }
            withContext(Dispatchers.Main) {
                delay(50)
                bind.recHunting.adapter = DocumentAdapter(idList,idLDList,fioList,nameDocList,icoList,bgDocList, requireActivity())
            }
        }






//создание 0й точи базы
        bind.txtBtnCircle.setOnClickListener {
            scopeDef.launch {
                if (db.getDao().isEmptyDoc()) {
                    db.getDao().insertDoc(DocumentDb(
                        0, "", "", "", "", "", "",
                        "", "", "", "", "", "", "", "", "", "",
                        "", "", "", "", "", "", "", "", "",
                        "", "", "", "", "", "", "", "", "",
                        "", "", "", "", "", "", "", "", "",
                        "", "", "", "", 0,0,0))
                }
            }
            (activity as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.docFragmentHolder, SelectNewDocFragment.newInstance())
                .commit()

        }


    }
    private fun typeVisibility(typeVis: MutableSet<String>, db:Maindb) {
        scopeDef.launch {
            db.getDao().getDoc().forEach { typeVis.add(it.type) }
            withContext(Dispatchers.Main) {
                if (typeVis.contains("main")) {
                    bind.txtDocs.visibility = View.VISIBLE
                    bind.recDocs.visibility = View.VISIBLE
                }
                if (typeVis.contains("education")) {
                    bind.txtEducation.visibility = View.VISIBLE
                    bind.recEducation.visibility = View.VISIBLE
                }
                if (typeVis.contains("hunting")) {
                    bind.txtHunting.visibility = View.VISIBLE
                    bind.recHunting.visibility = View.VISIBLE
                }
                if (typeVis.contains("marriage")) {
                    bind.txtMarriage.visibility = View.VISIBLE
                    bind.recMarriage.visibility = View.VISIBLE
                }
                if (typeVis.contains("medicine")) {
                    bind.txtMedicine.visibility = View.VISIBLE
                    bind.recMedicine.visibility = View.VISIBLE
                }
                if (typeVis.contains("taxes")) {
                    bind.txtTaxes.visibility = View.VISIBLE
                    bind.recTaxes.visibility = View.VISIBLE
                }
                if (typeVis.contains("transport")) {
                    bind.txtTransport.visibility = View.VISIBLE
                    bind.recTransport.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun animationStart() {
        bind.imgBtnCircle.animate().apply {
            duration = 400
            rotationYBy(360f)
        }
        bind.txtBtnCircle.animate().apply {
            duration = 400
            rotationYBy(360f)
        }
        bind.header.animate().apply {
            duration = 400
            rotationX(180f)
        }
        bind.txtHeader.animate().apply {
            duration = 400
            rotationX(180f)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = DocumentFragment()
    }
}
