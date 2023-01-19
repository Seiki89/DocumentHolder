package com.seiki.android.docholder.screens.work.cards.card_new_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentCardNewBinding
import com.seiki.android.docholder.model.CardModel

class CardNewFragment : Fragment() {
    private lateinit var bind: FragmentCardNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentCardNewBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[CardNewViewModel::class.java]

        animationStart()


        bind.imgBtnCircle.setOnClickListener {
            //выйти без сохранения
            APP.navController.navigate(R.id.action_cardNewFragment_to_cardFragment)
        }

        bind.imgSaveBtn.setOnClickListener {
            //сохранить и выйти
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
            viewModel.insert(CardModel(null,bank, num1, num2, num3, num4, date1, date2, name, cvc, pin))

            APP.navController.navigate(R.id.action_cardNewFragment_to_cardFragment)
        }
    }


    private fun animationStart() {
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
}
