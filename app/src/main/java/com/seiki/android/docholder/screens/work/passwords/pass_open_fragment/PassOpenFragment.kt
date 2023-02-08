package com.seiki.android.docholder.screens.work.passwords.pass_open_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentPassOpenBinding
import com.seiki.android.docholder.getSerializableCompat
import com.seiki.android.docholder.model.NoteModel
import com.seiki.android.docholder.model.PassModel
import com.seiki.android.docholder.screens.work.notes.note_open_fragment.NoteOpenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PassOpenFragment : Fragment() {
    private lateinit var bind: FragmentPassOpenBinding
    lateinit var currentNote: PassModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentPassOpenBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentNote = arguments?.getSerializableCompat("Pass", PassModel::class.java) as PassModel
        val viewModel = ViewModelProvider(this)[PassOpenViewModel::class.java]

        init()

        bind.imgBtnCircle.setOnClickListener {
            //выйти не сохраняя
            APP.navController.navigate(R.id.action_passOpenFragment_to_passFragment)
        }

        bind.imgSaveBtn.setOnClickListener { saveAndExit(viewModel) }

    }

    private fun saveAndExit(viewModel: PassOpenViewModel) {
        //сохранить и выйти
        val id = currentNote.id
        val service = currentNote.service
        val login = bind.edTxtLogin.text.toString()
        val pass = bind.edTxtPass.text.toString()
        viewModel.insert(PassModel(id, service, login, pass)) {}
        APP.navController.navigate(R.id.action_passOpenFragment_to_passFragment)
    }

    private fun init() {
        animationStart()

        bind.txtHeader.text = currentNote.service
        bind.edTxtLogin.setText(currentNote.login)
        bind.edTxtPass.setText(currentNote.password)

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