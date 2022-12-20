package com.example.documentholder.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.DataBase.NoteDb
import com.example.documentholder.R
import com.example.documentholder.databinding.FragmentNoteNewBinding
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.launch

class NoteNewFragment() : Fragment() {
    lateinit var bind: FragmentNoteNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentNoteNewBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Maindb.db(requireActivity())
        animationStart()
//выход не сохраняя изменений
        bind.imgBtnCircle.setOnClickListener {
            exit()
        }
//сохранить и выйти
        bind.imgSaveBtn.setOnClickListener {
            val txtName = bind.edTxtName.text.toString()
            val txtText = bind.edTxtText.text.toString()
            val uri = 0
            scopeDef.launch {db.getDao().insertNote(NoteDb(null,txtName,txtText,uri))}
            Thread.sleep(30)
            exit()
        }

    }
    private fun exit(){
        (activity as FragmentActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.docFragmentHolder, NoteFragment.newInstance())
            .commit()
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
    companion object {

        @JvmStatic
        fun newInstance() = NoteNewFragment()
    }
}