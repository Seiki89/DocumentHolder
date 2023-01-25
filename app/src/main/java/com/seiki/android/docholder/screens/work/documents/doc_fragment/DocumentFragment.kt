package com.seiki.android.docholder.screens.work.documents.doc_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.REPOSITORY_DOC
import com.seiki.android.docholder.databinding.FragmentDocumentBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc
import kotlinx.coroutines.*

class DocumentFragment : Fragment() {
    private lateinit var bind: FragmentDocumentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DocAdapter
    private lateinit var viewModel: DocumentViewModel
    private var list = listOf<DocModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentDocumentBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun click(docModel: DocModel,type:Int){
        val bundle = Bundle()
        bundle.putSerializable("Doc",docModel)
        bundle.putInt("type", type)
        APP.navController.navigate(R.id.action_documentFragment_to_documentNewFragment, bundle)
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[DocumentViewModel::class.java]
        viewModel.initDataBase()
        viewModel.getAllDocs().observe(viewLifecycleOwner) { list = it }

        animationStart()
        firstStart()

        CoroutineScope(Dispatchers.Main).launch {
            delay(300)
            typeVisibility(list)
            docRecycle(list)
            transportRecycle(list)
            taxesRecycle(list)
            educationRecycle(list)
            medicineRecycle(list)
            marriageRecycle(list)
            huntingRecycle(list)
        }

        bind.imgBtnCircle.setOnClickListener {
            APP.navController.navigate(R.id.action_documentFragment_to_selectNewDocFragment)
        }

    }

    private fun docRecycle(list: List<DocModel>) {
        //выделить "Основные документы" в отдельный рецайкл
        val listDoc = mutableListOf<DocModel>()
        list.forEach {
            if (it.type == "main"){
                listDoc.add(it)
            }
        }
        recyclerView = bind.recDocs
        adapter = DocAdapter()
        recyclerView.adapter = adapter
        adapter.differ.submitList(listDoc)
    }

    private fun transportRecycle(list: List<DocModel>) {
        //выделить "Основные документы" в отдельный рецайкл
        val listTrans = mutableListOf<DocModel>()
        list.forEach {
            if (it.type == "transport"){
                listTrans.add(it)
            }
        }
        recyclerView = bind.recTransport
        adapter = DocAdapter()
        recyclerView.adapter = adapter
        adapter.differ.submitList(listTrans)
    }

    private fun taxesRecycle(list: List<DocModel>) {
        //выделить "Основные документы" в отдельный рецайкл
        val listTaxes = mutableListOf<DocModel>()
        list.forEach {
            if (it.type == "taxes"){
                listTaxes.add(it)
            }
        }
        recyclerView = bind.recTaxes
        adapter = DocAdapter()
        recyclerView.adapter = adapter
        adapter.differ.submitList(listTaxes)
    }

    private fun educationRecycle(list: List<DocModel>) {
        //выделить "Основные документы" в отдельный рецайкл
        val listEduc = mutableListOf<DocModel>()
        list.forEach {
            if (it.type == "education"){
                listEduc.add(it)
            }
        }
        recyclerView = bind.recEducation
        adapter = DocAdapter()
        recyclerView.adapter = adapter
        adapter.differ.submitList(listEduc)
    }

    private fun medicineRecycle(list: List<DocModel>) {
        //выделить "Основные документы" в отдельный рецайкл
        val listMed = mutableListOf<DocModel>()
        list.forEach {
            if (it.type == "medicine"){
                listMed.add(it)
            }
        }
        recyclerView = bind.recMedicine
        adapter = DocAdapter()
        recyclerView.adapter = adapter
        adapter.differ.submitList(listMed)
    }

    private fun marriageRecycle(list: List<DocModel>) {
        //выделить "Основные документы" в отдельный рецайкл
        val listMarriage = mutableListOf<DocModel>()
        list.forEach {
            if (it.type == "marriage"){
                listMarriage.add(it)
            }
        }
        recyclerView = bind.recMarriage
        adapter = DocAdapter()
        recyclerView.adapter = adapter
        adapter.differ.submitList(listMarriage)
    }

    private fun huntingRecycle(list: List<DocModel>) {
        //выделить "Основные документы" в отдельный рецайкл
        val listHunt = mutableListOf<DocModel>()
        list.forEach {
            if (it.type == "hunting"){
                listHunt.add(it)
            }
        }
        recyclerView = bind.recHunting
        adapter = DocAdapter()
        recyclerView.adapter = adapter
        adapter.differ.submitList(listHunt)
    }

    private fun firstStart() {
        //окно первой работы с документами
        CoroutineScope(Dispatchers.Default).launch {
            val check = REPOSITORY_DOC.checkDoc
            withContext(Dispatchers.Main) {
                if (check) {
                    bind.preStat.visibility = View.VISIBLE
                    zeroBaseInit()
                }
            }
        }
    }

    private fun zeroBaseInit() {
        //создание 0й точи базы
        bind.txtBtnCircle.setOnClickListener {
            viewModel.insert(ForDoc().zeroBaseInit)
            APP.navController.navigate(R.id.action_documentFragment_to_selectNewDocFragment)
        }
    }

    private fun typeVisibility(list: List<DocModel>) {
        val typeVis = mutableSetOf<String>()
        list.forEach { typeVis.add(it.type) }
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


    private fun animationStart() {
        //стартовая анмация
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

}



