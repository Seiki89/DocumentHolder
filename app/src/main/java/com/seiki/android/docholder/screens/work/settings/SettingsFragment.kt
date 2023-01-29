package com.seiki.android.docholder.screens.work.settings

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.databinding.FragmentSettingsBinding
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.model.SettModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SettingsFragment : Fragment() {
    private lateinit var bind: FragmentSettingsBinding
    private var listDocModel = listOf<DocModel>()
    private var listSettModel = listOf<SettModel>()
    private val viewModelDoc = DocumentViewModel(Application())
    private val viewModelSet = SettingsViewModel(Application())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentSettingsBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        viewModel.initDataBase()

        viewModelDoc.getAllDocs().observe(viewLifecycleOwner) { listDocModel = it }
        viewModelSet.getAllSett().observe(viewLifecycleOwner) { listSettModel = it }

        startAnimation()
        account()
        theme()
        login()

        bind.btnExit.setOnClickListener { ActivityCompat.finishAffinity(requireActivity()) }

    }

    private fun login(){
        bind.btnLogin.setOnClickListener {
            if (bind.SetCard.visibility == View.GONE) {
                bind.SetCard.visibility = View.VISIBLE
            }
            bind.SetCard.animate().apply {
                duration = 200
                rotationXBy(360f)
            }
            bind.apply {
                btnDayTheme.visibility = View.GONE
                btnGradienTheme.visibility = View.GONE
                btnDarkTheme.visibility = View.GONE
                edPreFIO.visibility = View.GONE
                edPreBdate.visibility = View.GONE
                edPreBPos.visibility = View.GONE
                edPreSex.visibility = View.GONE
                btnSave.visibility = View.GONE
                oldPass.visibility = View.VISIBLE
                newPass.visibility = View.VISIBLE
                btnSavePass.visibility = View.VISIBLE
                switchPass.visibility = View.VISIBLE
                switchBio.visibility = View.VISIBLE
            }
        }
    }

    private fun theme() {
        //кнопка смены темы
        bind.btnChangeTheme.setOnClickListener {
            if (bind.SetCard.visibility == View.GONE) {
                bind.SetCard.visibility = View.VISIBLE
            }
            bind.SetCard.animate().apply {
                duration = 200
                rotationXBy(360f)
            }
            bind.apply {
                btnDayTheme.visibility = View.VISIBLE
                btnGradienTheme.visibility = View.VISIBLE
                btnDarkTheme.visibility = View.VISIBLE
                edPreFIO.visibility = View.GONE
                edPreBdate.visibility = View.GONE
                edPreBPos.visibility = View.GONE
                edPreSex.visibility = View.GONE
                btnSave.visibility = View.GONE
                oldPass.visibility = View.GONE
                newPass.visibility = View.GONE
                btnSavePass.visibility = View.GONE
                switchPass.visibility = View.GONE
                switchBio.visibility = View.GONE
            }
        }

        bind.btnDarkTheme.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        bind.btnDayTheme.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun account() {
        //настройки аккаунта
        CoroutineScope(Dispatchers.Main).launch {
            delay(100)
            //презагрузка данных аккаунта
            listDocModel.forEach {
                if (it.id == 0) {
                    bind.edPreFIO.setText(it.fio)
                    bind.edPreBdate.setText(it.birth_date)
                    bind.edPreBPos.setText(it.birth_pos)
                    bind.edPreSex.setText(it.sex)
                }
            }
        }
        bind.btnAcc.setOnClickListener {
            if (bind.SetCard.visibility == View.GONE) {
                bind.SetCard.visibility = View.VISIBLE
            }
            bind.SetCard.animate().apply {
                duration = 200
                rotationXBy(360f)
            }
            bind.apply {
                btnDayTheme.visibility = View.GONE
                btnGradienTheme.visibility = View.GONE
                btnDarkTheme.visibility = View.GONE
                edPreFIO.visibility = View.VISIBLE
                edPreBdate.visibility = View.VISIBLE
                edPreBPos.visibility = View.VISIBLE
                edPreSex.visibility = View.VISIBLE
                btnSave.visibility = View.VISIBLE
                oldPass.visibility = View.GONE
                newPass.visibility = View.GONE
                btnSavePass.visibility = View.GONE
                switchPass.visibility = View.GONE
                switchBio.visibility = View.GONE
            }
        }
        bind.btnSave.setOnClickListener {
            if (listDocModel.isEmpty()) {
                listDocModel = listOf(ForDoc().zeroBaseInit)
            }

            listDocModel.forEach {
                it.fio = bind.edPreFIO.text.toString()
                it.birth_date = bind.edPreBdate.text.toString()
                it.birth_pos = bind.edPreBPos.text.toString()
                it.sex = bind.edPreSex.text.toString()
                viewModelDoc.insert(it)
                bind.btnSave.animate().apply {
                    duration = 300
                    rotationYBy(360f)
                }
                bind.btnSave.text = "Сохранено!"
            }
        }
    }

    private fun startAnimation() {
        bind.header.animate().apply {
            duration = 400
            rotationXBy(360f)
        }
    }

}