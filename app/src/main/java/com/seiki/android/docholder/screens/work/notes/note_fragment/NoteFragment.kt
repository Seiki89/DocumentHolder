package com.seiki.android.docholder.screens.work.notes.note_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentNoteBinding
import com.seiki.android.docholder.model.NoteModel


class NoteFragment : Fragment() {
    private lateinit var bind: FragmentNoteBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentNoteBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        bind.txtBtnCircle.setOnClickListener {
            //создать новую заметку
            APP.navController.navigate(R.id.action_noteFragment_to_noteNewFragment)
        }
    }

    fun click(noteModel: NoteModel){
        val bundle = Bundle()
        bundle.putSerializable("Note",noteModel)
        APP.navController.navigate(R.id.action_noteFragment_to_noteOpenFragment, bundle)
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[NoteFragmentViewModel::class.java]
        viewModel.initDataBase()

        animationStart()

        recyclerView = bind.NoteRecycle
        adapter = NoteAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllNotes().observe(viewLifecycleOwner) { listNotes ->
            adapter.setList(listNotes.asReversed())
        }
    }

    private fun animationStart() {
        //стартовая анимация
        bind.imgBtnCircle.animate().apply {
            duration = 400
            rotationYBy(360f)
        }
        bind.txtBtnCircle.animate().apply {
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
