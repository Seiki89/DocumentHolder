package com.seiki.android.docholder.screens.work.notes.note_fragment

import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.seiki.android.docholder.LOG
import com.seiki.android.docholder.R
import com.seiki.android.docholder.model.NoteModel

class NoteAdapter() : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val viewModel = NoteFragmentViewModel(Application())

    var listNote = emptyList<NoteModel>()

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameHolder: TextView = itemView.findViewById(R.id.rcTxtNoteName)
        val delHolder: ImageView = itemView.findViewById(R.id.rcImgBtnNoteDel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_note_recycle,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.nameHolder.text = listNote[position].name
        holder.delHolder.setOnClickListener {
            viewModel.delete(listNote[holder.adapterPosition]){}
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<NoteModel>){
        listNote = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: NoteViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            NoteFragment().click(listNote[holder.adapterPosition])

        }
    }

    override fun onViewDetachedFromWindow(holder: NoteViewHolder) {
        holder.itemView.setOnClickListener(null)
    }




}

