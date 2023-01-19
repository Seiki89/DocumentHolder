package com.seiki.android.docholder.screens.work.cards.card_fragment

import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seiki.android.docholder.R
import com.seiki.android.docholder.model.CardModel


class CardAdapter : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {
    private val viewModel = CardFragmentViewModel(Application())
    var listCard = emptyList<CardModel>()

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameHolder: TextView = itemView.findViewById(R.id.rcTxtServiceAdapter)
        val descHolder: TextView = itemView.findViewById(R.id.rcTxtLoginAdapter)
        val delHolder: ImageView = itemView.findViewById(R.id.rcImgBtnDeleteAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_pass_card_recycle, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.nameHolder.text = listCard[position].bank
        holder.descHolder.text = listCard[position].name

        holder.delHolder.setOnClickListener {
            //удалить карту
            viewModel.delete(listCard[holder.adapterPosition]) {}
        }
    }

    override fun getItemCount(): Int {
        return listCard.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<CardModel>) {
        listCard = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: CardViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            CardFragment().click(listCard[holder.adapterPosition])

        }
    }

    override fun onViewDetachedFromWindow(holder: CardViewHolder) {
        holder.itemView.setOnClickListener(null)
    }




}