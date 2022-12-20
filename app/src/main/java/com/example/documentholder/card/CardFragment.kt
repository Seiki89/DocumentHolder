package com.example.documentholder.card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.R
import com.example.documentholder.WorkActivity
import com.example.documentholder.databinding.FragmentCardBinding
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CardFragment : Fragment() {
    lateinit var bind: FragmentCardBinding
    val idList = mutableListOf<Int?>()
    val bankList = mutableListOf<String>()
    val nameList= mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentCardBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Maindb.db(requireActivity())
        animationStart()

        val recyclerView: RecyclerView = bind.cardRecycle
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = CardAdapter(idList,bankList,nameList, activity as WorkActivity)
        scopeDef.launch {
            db.getDao().getCard().toList().forEach {
                idList.add(it.id)
                bankList.add(it.bank)
                nameList.add(it.name)
            }
            withContext(Dispatchers.Main) {
                delay(50)
                recyclerView.adapter = CardAdapter(idList,bankList,nameList, activity as WorkActivity)
            }
        }

        bind.txtBtnCircle.setOnClickListener {
            (activity as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.docFragmentHolder, CardNewFragment.newInstance())
                .commit()

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
        fun newInstance() = CardFragment()
    }
}