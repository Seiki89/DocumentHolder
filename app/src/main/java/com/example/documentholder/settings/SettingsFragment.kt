package com.example.documentholder.settings

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.databinding.FragmentSettingsBinding
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsFragment : Fragment() {
    lateinit var bind: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentSettingsBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Maindb.db(requireActivity())
        //презагрузка данных аккаунта
        scopeDef.launch {
            db.getDao().getDoc().forEach {
                if (it.id == 0){
                    withContext(Dispatchers.Main) {
                        bind.edPreFIO.setText(it.fio)
                        bind.edPreBdate.setText(it.birth_date)
                        bind.edPreBPos.setText(it.birth_pos)
                        bind.edPreSex.setText(it.sex)
                    }
                }
            }
        }

        //кнопка настройки аккаунта
        bind.btnAcc.setOnClickListener {
            if (bind.SetCard.visibility == View.GONE){bind.SetCard.visibility = View.VISIBLE}
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
            }
        }
        //кнопка сохранения изменений в аккаунте
        bind.btnSave.setOnClickListener {
            scopeDef.launch {
                db.getDao().getDoc().forEach {
                    it.fio = bind.edPreFIO.text.toString()
                    it.birth_date = bind.edPreBdate.text.toString()
                    it.birth_pos = bind.edPreBPos.text.toString()
                    it.sex = bind.edPreSex.text.toString()
                    db.getDao().insertDoc(it)
                    withContext(Dispatchers.Main) {
                        bind.btnSave.animate().apply {
                            duration = 300
                            rotationYBy(360f)
                        }
                        bind.btnSave.text = "Сохранено!"
                    }
                }
            }
        }

        //кнопка смены темы
        bind.btnChangeTheme.setOnClickListener {
            if (bind.SetCard.visibility == View.GONE){bind.SetCard.visibility = View.VISIBLE}
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
            }
        }

        bind.btnDarkTheme.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        bind.btnDayTheme.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }




        //кнопка выхода
        bind.btnExit.setOnClickListener {finishAffinity(requireActivity())}
    }

    companion object {

        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}