package com.example.documentholder.document

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.documentholder.DataBase.DocumentDb
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.R
import com.example.documentholder.WorkActivity
import com.example.documentholder.misc.DataModel
import com.example.documentholder.misc.Log
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.launch

class DocumentAdapter (
    val idList: MutableList<Int?>,
    val idLDList: MutableList<Int>,
    val fioList: MutableList<String>,
    val nameDocList: MutableList<String>,
    val icoList: MutableList<Int>,
    val bgDocList: MutableList<Int>,

    activity: FragmentActivity,
    ): RecyclerView.Adapter<DocumentAdapter.MyDocViewHolder>() {
        private val dataModel: DataModel by activity.viewModels()
        val db = Maindb.db(activity)
        val act = activity

        class MyDocViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val imgIco: ImageView = itemView.findViewById(R.id.rcDocIcoAdapter)
            val txtName: TextView = itemView.findViewById(R.id.rcDocTxtNameAdapter)
            val typeDoc: TextView = itemView.findViewById(R.id.rcDocTxtTypeAdapter)
            val itemCard: CardView = itemView.findViewById(R.id.rcDocCardAdapter)
            val btnDel: CardView = itemView.findViewById(R.id.rcDocBtnDel)
            val btnEdit: CardView = itemView.findViewById(R.id.rcDocBtnEdit)
            val bgCard: TextView = itemView.findViewById(R.id.rcDocTxtForBGAdapter)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDocViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_docs_recycle, parent, false)
            return MyDocViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyDocViewHolder, position: Int) {
            holder.imgIco.setImageResource(icoList[position])
            holder.txtName.text = ForDoc().capitalize(fioList[position])
            holder.typeDoc.text = nameDocList[position]
            holder.bgCard.setBackgroundResource(bgDocList[position])

            holder.itemCard.setOnClickListener {
                dataModel.msgID.value = idLDList[position]
                dataModel.msgDocumentID.value = idList[position]
                act.supportFragmentManager
                    .beginTransaction()
                    /**New-Open*/.replace(R.id.docFragmentHolder, DocumentNewFragment.newInstance("open"))
                    .commit()
            }

            holder.itemCard.setOnLongClickListener {
                holder.btnDel.visibility = View.VISIBLE
                holder.btnEdit.visibility = View.VISIBLE
                true}
            holder.btnDel.setOnLongClickListener {
                val posForDel = idList[position]!!
                scopeDef.launch { db.getDao().deleteByDocId(posForDel)}
                idList.removeAt(position)
                idLDList.removeAt(position)
                fioList.removeAt(position)
                nameDocList.removeAt(position)
                icoList.removeAt(position)
                bgDocList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,itemCount)
                true}
            holder.btnEdit.setOnLongClickListener {
                dataModel.msgID.value = idLDList[position]
                dataModel.msgDocumentID.value = idList[position]
                act.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.docFragmentHolder, DocumentNewFragment.newInstance("edit"))
                    .commit()
                true
            }
            holder.btnDel.setOnClickListener {
                holder.btnDel.visibility = View.GONE
                holder.btnEdit.visibility = View.GONE
                Toast.makeText(act, "Зажмите кнопку, чтобы удалить", Toast.LENGTH_LONG).show()
            }
            holder.btnEdit.setOnClickListener {
                holder.btnDel.visibility = View.GONE
                holder.btnEdit.visibility = View.GONE
                Toast.makeText(act, "Зажмите кнопку, чтобы редактировать", Toast.LENGTH_LONG).show()
            }

        }


        override fun getItemCount() = idList.size
    }

