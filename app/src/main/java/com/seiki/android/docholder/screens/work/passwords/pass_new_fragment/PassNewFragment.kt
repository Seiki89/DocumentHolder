package com.seiki.android.docholder.screens.work.passwords.pass_new_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentPassNewBinding
import com.seiki.android.docholder.model.NoteModel
import com.seiki.android.docholder.model.PassModel
import com.seiki.android.docholder.screens.work.notes.note_new_fragment.NoteNewViewModel

class PassNewFragment : Fragment() {
    lateinit var bind: FragmentPassNewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentPassNewBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[PassNewViewModel::class.java]

        animationStart()

        bind.imgBtnCircle.setOnClickListener {
            //выйти без сохранения
            APP.navController.navigate(R.id.action_passNewFragment_to_passFragment)
        }


        bind.imgSaveBtn.setOnClickListener {
            //сохранить пароль
            val service = bind.edTxtPassService.text.toString()
            val login = bind.edTxtPassLogin.text.toString()
            val pass = bind.eddTxtPassPass.text.toString()
            viewModel.insert(PassModel(null,service,login,pass)){}
            APP.navController.navigate(R.id.action_passNewFragment_to_passFragment)
        }
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