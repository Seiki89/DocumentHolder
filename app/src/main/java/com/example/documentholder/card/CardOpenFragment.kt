package com.example.documentholder.card

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.documentholder.DataBase.CardDb
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.R
import com.example.documentholder.databinding.FragmentCardOpenBinding
import com.example.documentholder.misc.DataModel
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CardOpenFragment : Fragment() {
    lateinit var bind: FragmentCardOpenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentCardOpenBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Maindb.db(requireActivity())
        val dataModel: DataModel by requireActivity().viewModels()
        var idPD = 0
        dataModel.msgID.observe(activity as LifecycleOwner) { idPD = it.toString().toInt() }
        animationStart()

        scopeDef.launch {
            db.getDao().getCard().toList().forEach {
                if (idPD == it.id) {
                    withContext(Dispatchers.Main) {
                        bind.edBank.setText(it.bank)
                        bind.edNum1.setText(it.num1)
                        bind.edNum2.setText(it.num2)
                        bind.edNum3.setText(it.num3)
                        bind.edNum4.setText(it.num4)
                        bind.edDate1.setText(it.date1)
                        bind.edDate2.setText(it.date2)
                        bind.edName.setText(it.name)
                        bind.edCvco.setText(it.cvc)
                        bind.edPino.setText(it.pin)
                    }
                }
            }
        }

        bind.imgSaveBtn.setOnClickListener {
            scopeDef.launch {
                db.getDao().insertCard(CardDb(
                    idPD,
                    bind.edBank.text.toString(),
                    bind.edNum1.text.toString(),
                    bind.edNum2.text.toString(),
                    bind.edNum3.text.toString(),
                    bind.edNum4.text.toString(),
                    bind.edDate1.text.toString(),
                    bind.edDate2.text.toString(),
                    bind.edName.text.toString(),
                    bind.edCvco.text.toString(),
                    bind.edPino.text.toString(),
                ))
            }
            exit()
        }

        bind.imgBtnVisibility.setOnClickListener {
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

        bind.imgBtnShare.setOnClickListener {
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

        bind.btnShareCard.setOnClickListener {
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
        bind.btnShareNum.setOnClickListener {
            shareInis()
            val sendText = "${bind.edNum1.text}-${bind.edNum2.text}-${bind.edNum3.text}-${bind.edNum4.text}"

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, sendText)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }

        bind.imgBtnCircle.setOnClickListener { exit() }


    }

    private fun shareVis() {
        bind.cardCopy.visibility = View.VISIBLE
        bind.btnShareCard.visibility = View.VISIBLE
        bind.btnShareNum.visibility = View.VISIBLE
    }

    private fun shareInis() {
        bind.cardCopy.visibility = View.INVISIBLE
        bind.btnShareCard.visibility = View.INVISIBLE
        bind.btnShareNum.visibility = View.INVISIBLE
    }

    private fun exit() {
        (activity as FragmentActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.docFragmentHolder, CardFragment.newInstance())
            .commit()
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

    companion object {
        @JvmStatic
        fun newInstance() = CardOpenFragment()
    }
}