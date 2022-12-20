package com.example.documentholder.note

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.DataBase.NoteDb
import com.example.documentholder.R
import com.example.documentholder.databinding.FragmentNoteOpenBinding
import com.example.documentholder.misc.DataModel
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NoteOpenFragment : Fragment() {
    lateinit var bind: FragmentNoteOpenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentNoteOpenBinding.inflate(inflater)
        return bind.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Maindb.db(requireActivity())
        val dataModel: DataModel by requireActivity().viewModels()
        var idLD = 0
        dataModel.msgID.observe(activity as LifecycleOwner) { idLD = it.toString().toInt() }
        animationStart()


       scopeDef.launch {
           var name = ""
           var text = ""
           db.getDao().getNote().toList().forEach {
               if (idLD == it.id) {
                   name = it.name
                   text = it.text
               }
           }
           withContext(Dispatchers.Main){
               bind.txtHeader.setText(name)
               bind.edTxtText.setText(text)
           }
       }

        bind.imgSaveBtn.setOnClickListener {
            val name = bind.txtHeader.text.toString()
            val text = bind.edTxtText.text.toString()
            scopeDef.launch {db.getDao().insertNote(NoteDb(idLD, name, text, 0))}
            exit()
        }

        bind.imgBtnShareN.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, bind.edTxtText.text)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }

        bind.imgBtnCircle.setOnClickListener { exit() }

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
        fun newInstance() = NoteOpenFragment()

    }
}