package com.seiki.android.docholder.screens.work.settings

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.model.SettModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc

class SettVMLogic(private val settingsFragment: SettingsFragment)
{
    private var listDocModel = listOf<DocModel>()
    private var listSettModel = listOf<SettModel>()
    private val viewModelDoc = DocumentViewModel(Application())
    private val viewModelSet = SettingsViewModel(Application())

     fun initDB() {
         //перегружаем списки
        val viewModel = ViewModelProvider(settingsFragment)[SettingsViewModel::class.java]
        viewModel.initDataBase()
        viewModelDoc.getAllDocs().observe(settingsFragment.viewLifecycleOwner) { listDocModel = it }
        viewModelSet.getAllSett().observe(settingsFragment.viewLifecycleOwner) { listSettModel = it }
    }

    fun login():List<String> {
        return if (listSettModel.isEmpty()) {
            listOf("ведите новый пароль","подтвердите новый пароль")
        }else{
            listOf("Старый пароль","Новый пароль")
        }
    }

    fun saveLogin(oldPass:String,newPass:String,switchPass:Boolean,switchBio:Boolean):Int {
        //действия кнопки сохранения логина
        val res: Int
        var oldPassInit = 0
        listSettModel.forEach { oldPassInit = it.pass }

        //проверяем существует ли список-создаем
        if (listSettModel.isEmpty()) {
            listSettModel = listOf(SettModel(0, 0, switchPass = false, switchBio = false))
            viewModelSet.insert(SettModel(0, 0, switchPass = false, switchBio = false))
        }

        if (oldPassInit == 0) {
            if (oldPass == newPass &&
                newPass.isNotBlank() &&
                newPass.toInt() != 0
            ) {
                listSettModel.forEach {
                    it.pass = newPass.toInt()
                    it.switchPass = switchPass
                    it.switchBio = switchBio
                    viewModelSet.insert(it)
                }
                res = 1
            } else {
                res = 2
            }
        } else {
            if (newPass.isNotBlank() &&
                oldPass.isNotBlank() &&
                oldPassInit == oldPass.toInt() &&
               newPass.toInt() != 0
            ) {
                listSettModel.forEach {
                    it.pass = newPass.toInt()
                    it.switchPass = switchPass
                    it.switchBio = switchBio
                    viewModelSet.insert(it)
                }
                res = 3
            } else {
                res = 4
            }

        }
        return res
    }

     fun account():Map<String,String> {
        //настройки аккаунта
         val listLoad = mutableMapOf("fio" to "","b_date" to "","b_pos" to "","sex" to "")

         listDocModel.forEach {
                if (it.id == 0) {
                    listLoad["fio"] = it.fio
                    listLoad["b_date"] = it.birth_date
                    listLoad["b_pos"] = it.birth_pos
                    listLoad["sex"] = it.sex
                }
            }

         return listLoad
    }

    fun saveAccount(fio:String,b_date:String,b_pos:String,sex:String) {
        //действие кнопки сохранить данные аккаунта
        if (listDocModel.isEmpty()) {
            listDocModel = listOf(ForDoc().zeroBaseInit)
        }
        listDocModel.forEach {
            it.fio = fio
            it.birth_date = b_date
            it.birth_pos = b_pos
            it.sex = sex
            viewModelDoc.insert(it)
        }

    }

}