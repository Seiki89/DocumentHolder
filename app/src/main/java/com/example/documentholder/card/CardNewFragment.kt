package com.example.documentholder.card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.documentholder.DataBase.CardDb
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.R
import com.example.documentholder.databinding.FragmentCardNewBinding
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.launch


class CardNewFragment : Fragment() {
    lateinit var bind:FragmentCardNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentCardNewBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Maindb.db(requireActivity())
        animationStart()


        bind.imgBtnCircle.setOnClickListener { exit() }

        bind.imgSaveBtn.setOnClickListener {
            scopeDef.launch {
                val bank = bind.edBank.text.toString()
                val num1 = bind.edNum1.text.toString()
                val num2 = bind.edNum2.text.toString()
                val num3 = bind.edNum3.text.toString()
                val num4 = bind.edNum4.text.toString()
                val date1 = bind.edDate1.text.toString()
                val date2 = bind.edDate2.text.toString()
                val name = bind.edName.text.toString()
                val cvc = bind.edCvc.text.toString()
                val pin = bind.edPin.text.toString()
                db.getDao().insertCard(CardDb(null,bank, num1, num2, num3, num4, date1, date2, name, cvc, pin))
            }
            exit()
        }


    }

    private fun exit(){
        (activity as FragmentActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.docFragmentHolder, CardFragment.newInstance())
            .commit()
    }

    private fun animationStart(){
        bind.imgSaveBtn.animate().apply {
            duration = 400
            rotationYBy(360f)
        }
        bind.imgSaveIco.animate().apply {
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
        fun newInstance()=CardNewFragment()
    }
}