package com.seiki.android.docholder.screens.work.notes.note_open_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentNoteOpenBinding
import com.seiki.android.docholder.getSerializableCompat
import com.seiki.android.docholder.model.NoteModel


class NoteOpenFragment : Fragment() {
    private lateinit var bind: FragmentNoteOpenBinding
    lateinit var currentNote: NoteModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentNoteOpenBinding.inflate(inflater)
        return bind.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentNote = arguments?.getSerializableCompat("Note",NoteModel::class.java) as NoteModel

        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[NoteOpenViewModel::class.java]
        animationStart()

        bind.txtHeader.text = currentNote.name
        bind.edTxtText.setText(currentNote.text)

        bind.imgSaveBtn.setOnClickListener {
            //сохранить изменения и выйти
            val id = currentNote.id
            val name = currentNote.name
            val text = bind.edTxtText.text.toString()
            viewModel.insert(NoteModel(id,name,text,0)){}
            APP.navController.navigate(R.id.action_noteOpenFragment_to_noteFragment)
        }

        bind.imgBtnDel.setOnClickListener {
            //удалить заметку
            viewModel.delete(currentNote){}
            APP.navController.navigate(R.id.action_noteOpenFragment_to_noteFragment)
        }

        bind.imgBtnShareN.setOnClickListener {
            //поделится заметкой
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, bind.edTxtText.text)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }

        bind.imgBtnCircle.setOnClickListener {
            //выйти не сохраняя
            APP.navController.navigate(R.id.action_noteOpenFragment_to_noteFragment)
        }
    }

    private fun animationStart(){
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