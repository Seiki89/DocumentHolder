package com.seiki.android.docholder.screens.work.documents.doc_fragment

import android.app.Application
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.seiki.android.docholder.R
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel
import com.seiki.android.docholder.screens.work.documents.doc_new_fragment.ForDoc
import java.io.File


class DocAdapter() : RecyclerView.Adapter<DocAdapter.DocViewHolder>() {
    private val viewModel = DocumentViewModel(Application())

    class DocViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgIco: ImageView = itemView.findViewById(R.id.rcDocIcoAdapter)
        val txtName: TextView = itemView.findViewById(R.id.rcDocTxtNameAdapter)
        val typeDoc: TextView = itemView.findViewById(R.id.rcDocTxtTypeAdapter)
        val btnDel: CardView = itemView.findViewById(R.id.rcDocBtnDel)
        val itemCard: CardView = itemView.findViewById(R.id.rcDocCardAdapter)
        val btnEdit: CardView = itemView.findViewById(R.id.rcDocBtnEdit)
        val bgCard: TextView = itemView.findViewById(R.id.rcDocTxtForBGAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_docs_recycle, parent, false)
        return DocViewHolder(view)
    }

    override fun onBindViewHolder(holder: DocViewHolder, position: Int) {
        val listDoc = differ.currentList

        holder.imgIco.setImageResource(listDoc[position].ico)
        holder.txtName.text = ForDoc().capitalize(listDoc[position].fio)
        holder.typeDoc.text = listDoc[position].NameDoc
        holder.bgCard.setBackgroundResource(listDoc[position].bgDoc)

        holder.itemCard.setOnClickListener {
            DocumentFragment().click(listDoc[holder.adapterPosition], 13)
        }

        holder.itemCard.setOnLongClickListener {
            holder.btnDel.visibility = View.VISIBLE
            holder.btnEdit.visibility = View.VISIBLE
            true
        }

        holder.btnDel.setOnClickListener {
            holder.btnDel.visibility = View.GONE
            holder.btnEdit.visibility = View.GONE
        }

        holder.btnEdit.setOnClickListener {
            holder.btnDel.visibility = View.GONE
            holder.btnEdit.visibility = View.GONE
        }

        holder.btnEdit.setOnLongClickListener {
            DocumentFragment().click(listDoc[holder.adapterPosition], 12)
            true
        }

        holder.btnDel.setOnLongClickListener {
            stringToFileWithDelete(differ.currentList[position].photo1)
            stringToFileWithDelete(differ.currentList[position].photo2)
            stringToFileWithDelete(differ.currentList[position].photo3)
            stringToFileWithDelete(differ.currentList[position].photo4)

            viewModel.delete(listDoc[holder.adapterPosition]) {}
            val listAfterDel = mutableListOf<DocModel>()
            for (i in 0 until  differ.currentList.size){
            listAfterDel.add(differ.currentList[i])}
            listAfterDel.removeAt(position)
            differ.submitList(listAfterDel)
            notifyItemRangeChanged(0,itemCount)
            true
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<DocModel>() {
        //контроль списка через DiffUtil
        override fun areItemsTheSame(oldItem: DocModel, newItem: DocModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DocModel, newItem: DocModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallback)

    private fun stringToFileWithDelete(value: String){
        val uri = Uri.parse(value)
        val path = uri.path
        val file = File(path!!)
        file.delete()
    }
}




