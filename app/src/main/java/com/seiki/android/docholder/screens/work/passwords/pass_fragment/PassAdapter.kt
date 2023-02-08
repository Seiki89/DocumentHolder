package com.seiki.android.docholder.screens.work.passwords.pass_fragment

import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seiki.android.docholder.R
import com.seiki.android.docholder.model.PassModel



class PassAdapter: RecyclerView.Adapter<PassAdapter.PassViewHolder>() {
    private val viewModel = PassFragmentViewModel(Application())

    private var listPass = emptyList<PassModel>()

    class PassViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameHolder: TextView = itemView.findViewById(R.id.rcTxtServiceAdapter)
        val descHolder: TextView = itemView.findViewById(R.id.rcTxtLoginAdapter)
        val delHolder: ImageView = itemView.findViewById(R.id.rcImgBtnDeleteAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_pass_card_recycle,parent,false)
        return PassViewHolder(view)

    }

    override fun onBindViewHolder(holder: PassViewHolder, position: Int) {

        holder.nameHolder.text = listPass[position].service
        holder.descHolder.text = listPass[position].login

        holder.delHolder.setOnClickListener {
            viewModel.delete(listPass[holder.adapterPosition]){}
        }
    }

    override fun getItemCount(): Int {
        return listPass.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<PassModel>){
        listPass = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: PassViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            PassFragment().click(listPass[holder.adapterPosition])

        }
    }

    override fun onViewDetachedFromWindow(holder: PassViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

}
