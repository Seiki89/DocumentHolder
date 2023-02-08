package com.seiki.android.docholder.screens.work.cards.card_open_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentCardOpenBinding
import com.seiki.android.docholder.getSerializableCompat
import com.seiki.android.docholder.model.CardModel

class CardOpenFragment : Fragment() {
    private lateinit var bind: FragmentCardOpenBinding
    lateinit var currentNote: CardModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentCardOpenBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentNote = arguments?.getSerializableCompat("Card",CardModel::class.java) as CardModel
        val viewModel = ViewModelProvider(this)[CardOpenViewModel::class.java]

        init()

        bind.imgBtnShare.setOnClickListener { shareMenu() }

        bind.btnShareCard.setOnClickListener { shareAllInfo() }

        bind.imgBtnVisibility.setOnClickListener { hideAndShowCvcAndPin() }

        bind.btnShareNum.setOnClickListener { shareNumCard() }

        bind.imgSaveBtn.setOnClickListener { saveEndExit(viewModel) }

        bind.imgBtnCircle.setOnClickListener {
            //выйти не сохраняя
            APP.navController.navigate(R.id.action_cardOpenFragment_to_cardFragment)
        }
    }

    private fun shareNumCard() {
        //поделится номером карты
        shareInis()
        val sendText =
            "${bind.edNum1.text}-${bind.edNum2.text}-${bind.edNum3.text}-${bind.edNum4.text}"

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, sendText)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Share To:"))
    }

    private fun shareAllInfo() {
        //поделтся картой
        shareInis()
        var sendText = ""
        sendText =
            "Банк: ${bind.edBank.text} " +
                    "\nНомер карты: ${bind.edNum1.text}-${bind.edNum2.text}-${bind.edNum3.text}-${bind.edNum4.text} " +
                    "\nСрок службы карты: ${bind.edDate1.text}-${bind.edDate2.text}" +
                    "\nИмя держателя карты: ${bind.edName.text}"

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, sendText)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Share To:"))
    }

    private fun shareMenu() {
        //меню поделится картой или номером карты
        if (bind.btnShareCard.visibility == View.INVISIBLE) {
            shareVis()
        } else {
            shareInis()
        }
        bind.imgBtnShare.animate().apply {
            duration = 400
            rotationX(360f)
        }.withEndAction {
            bind.imgBtnShare.animate().apply {
                duration = 1
                rotationX(0f)
            }
        }
        bind.imgShare.animate().apply {
            duration = 400
            rotationX(360f)
        }.withEndAction {
            bind.imgShare.animate().apply {
                duration = 1
                rotationX(0f)
            }
        }
    }

    private fun hideAndShowCvcAndPin() {
        //показать и скрыть pin-cvc
        bind.imgBtnVisibility.animate().apply {
            duration = 400
            rotationX(360f)
        }.withEndAction {
            bind.imgBtnVisibility.animate().apply {
                duration = 1
                rotationX(0f)
            }
        }
        bind.imgVis.animate().apply {
            duration = 400
            rotationX(360f)
        }.withEndAction {
            bind.imgVis.animate().apply {
                duration = 1
                rotationX(0f)
            }
        }
        if (bind.edCvco.visibility == View.INVISIBLE) {
            bind.edCvco.visibility = View.VISIBLE
            bind.edPino.visibility = View.VISIBLE
            bind.imgVis.setImageResource(R.drawable.ic_visibility_off)
        } else {
            bind.edCvco.visibility = View.INVISIBLE
            bind.edPino.visibility = View.INVISIBLE
            bind.imgVis.setImageResource(R.drawable.ic_visibility)
        }
    }

    private fun saveEndExit(viewModel: CardOpenViewModel) {
        //сохранить и выйти
        val id = currentNote.id
        val bank = bind.edBank.text.toString()
        val num1 = bind.edNum1.text.toString()
        val num2 = bind.edNum2.text.toString()
        val num3 = bind.edNum3.text.toString()
        val num4 = bind.edNum4.text.toString()
        val date1 = bind.edDate1.text.toString()
        val date2 = bind.edDate2.text.toString()
        val name = bind.edName.text.toString()
        val cvc = bind.edCvco.text.toString()
        val pin = bind.edPino.text.toString()
        viewModel.insert(CardModel(id, bank, num1, num2, num3, num4, date1, date2, name, cvc, pin)) {}

        APP.navController.navigate(R.id.action_cardOpenFragment_to_cardFragment)
    }

    private fun init() {
        animationStart()

        bind.edBank.setText(currentNote.bank)
        bind.edNum1.setText(currentNote.num1)
        bind.edNum2.setText(currentNote.num2)
        bind.edNum3.setText(currentNote.num3)
        bind.edNum4.setText(currentNote.num4)
        bind.edDate1.setText(currentNote.date1)
        bind.edDate2.setText(currentNote.date2)
        bind.edName.setText(currentNote.name)
        bind.edCvco.setText(currentNote.cvc)
        bind.edPino.setText(currentNote.pin)
            }

    private fun shareVis() {
        //показать "поделится"
        bind.cardCopy.visibility = View.VISIBLE
        bind.btnShareCard.visibility = View.VISIBLE
        bind.btnShareNum.visibility = View.VISIBLE
    }

    private fun shareInis() {
        //скрыть "поделится"
        bind.cardCopy.visibility = View.INVISIBLE
        bind.btnShareCard.visibility = View.INVISIBLE
        bind.btnShareNum.visibility = View.INVISIBLE
    }


    private fun animationStart() {
        //стартовая анимация
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