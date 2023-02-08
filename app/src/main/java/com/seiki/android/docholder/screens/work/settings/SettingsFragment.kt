package com.seiki.android.docholder.screens.work.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.seiki.android.docholder.databinding.FragmentSettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SettingsFragment : Fragment() {
    private lateinit var bind: FragmentSettingsBinding
    private lateinit var logic : SettVMLogic


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentSettingsBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logic = SettVMLogic(this@SettingsFragment)

        logic.initDB()
        startAnimation()

        bind.btnLogin.setOnClickListener {
            loginButtonInit()
            bind.oldPass.hint=logic.login()[0]
            bind.newPass.hint=logic.login()[1]
        }

        bind.btnSavePass.setOnClickListener { saveLoginInit() }

        bind.btnAcc.setOnClickListener {
            accountButtonInit()
            //logic.account()

        }

        bind.btnSave.setOnClickListener {
            logic.saveAccount(
                fio = bind.edPreFIO.text.toString(),
                b_date = bind.edPreBdate.text.toString(),
                b_pos = bind.edPreBPos.text.toString(),
                sex = bind.edPreSex.text.toString()
            )
            saveAccInit()
        }

        bind.btnExit.setOnClickListener {
            ActivityCompat.finishAffinity(requireActivity())
        }
    }

    private fun saveLoginInit() {
        when (logic.saveLogin(
            oldPass = bind.oldPass.text.toString(),
            newPass = bind.newPass.text.toString(),
            switchPass = bind.switchPass.isChecked,
            switchBio = bind.switchBio.isChecked
        )) {
            1 -> {
                CoroutineScope(Dispatchers.Main).launch {
                    bind.btnSavePass.animate().apply {
                        duration = 300
                        rotationYBy(360f)
                    }
                }
                bind.btnSavePass.text = "Сохранено!"
            }
            2 -> {
                Toast.makeText(requireContext(), "Ошибка: пароли не совпадают, или не введены",
                    Toast.LENGTH_LONG).show()
            }
            3 -> {
                CoroutineScope(Dispatchers.Main).launch {
                    bind.btnSavePass.animate().apply {
                        duration = 300
                        rotationYBy(360f)
                    }
                }
                bind.btnSavePass.text = "Сохранено!"
            }
            4 -> {
                Toast.makeText(requireContext(),
                    "Ошибка: пароли не совпадают или не введен неверный новый пароль",
                    Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun saveAccInit(){
        bind.btnSave.animate().apply {
            duration = 300
            rotationYBy(360f)
        }
        bind.btnSave.text = "Сохранено!"
    }

    private fun loginButtonInit() {
        if (bind.SetCard.visibility == View.GONE) {
            bind.SetCard.visibility = View.VISIBLE
        }
        bind.SetCard.animate().apply {
            duration = 200
            rotationXBy(360f)
        }
        loginVisible()
        accountInVisible()
    }

    private fun loginVisible() {
        bind.apply {
            oldPass.visibility = View.VISIBLE
            newPass.visibility = View.VISIBLE
            switchPass.visibility = View.VISIBLE
            switchBio.visibility = View.VISIBLE
            btnSavePass.visibility = View.VISIBLE
        }
    }

    private fun loginInVisible() {
        bind.apply {
            oldPass.visibility = View.GONE
            newPass.visibility = View.GONE
            switchPass.visibility = View.GONE
            switchBio.visibility = View.GONE
            btnSavePass.visibility = View.GONE
        }
    }

    private fun accountInVisible() {
        bind.apply {
            edPreFIO.visibility = View.GONE
            edPreBdate.visibility = View.GONE
            edPreBPos.visibility = View.GONE
            edPreSex.visibility = View.GONE
            btnSave.visibility = View.GONE
        }
    }

    private fun accountVisible() {
        bind.apply {
            edPreFIO.visibility = View.VISIBLE
            edPreBdate.visibility = View.VISIBLE
            edPreBPos.visibility = View.VISIBLE
            edPreSex.visibility = View.VISIBLE
            btnSave.visibility = View.VISIBLE
        }
    }

    private fun accountButtonInit() {
        if (bind.SetCard.visibility == View.GONE) {
            bind.SetCard.visibility = View.VISIBLE
        }
        bind.SetCard.animate().apply {
            duration = 200
            rotationXBy(360f)
        }
        accountVisible()
        loginInVisible()

        val getList = logic.account()

        bind.edPreFIO.setText(getList["fio"])
        bind.edPreBdate.setText(getList["b_date"])
        bind.edPreBPos.setText(getList["b_pos"])
        bind.edPreSex.setText(getList["sex"])
    }

    private fun startAnimation() {
        //стартовая анимация
        bind.header.animate().apply {
            duration = 400
            rotationXBy(360f)
        }
    }

}
