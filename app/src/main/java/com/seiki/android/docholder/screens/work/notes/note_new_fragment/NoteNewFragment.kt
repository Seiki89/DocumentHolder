package com.seiki.android.docholder.screens.work.notes.note_new_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentNoteNewBinding
import com.seiki.android.docholder.model.NoteModel


class NoteNewFragment() : Fragment() {
    private lateinit var bind: FragmentNoteNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentNoteNewBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[NoteNewViewModel::class.java]
        animationStart()

        bind.imgBtnCircle.setOnClickListener {
            //выйти не сохраняя
            APP.navController.navigate(R.id.action_noteNewFragment_to_noteFragment)
        }

        bind.imgSaveBtn.setOnClickListener {
            //сохранить и выйти
            val name = bind.edTxtName.text.toString()
            val text = bind.edTxtText.text.toString()
            viewModel.insert(NoteModel(null,name,text,0)){}
            APP.navController.navigate(R.id.action_noteNewFragment_to_noteFragment)
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
