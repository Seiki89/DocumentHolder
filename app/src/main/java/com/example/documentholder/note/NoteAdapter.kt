package com.example.documentholder.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.R
import com.example.documentholder.misc.DataModel
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.launch

class NoteAdapter(
    val idList: MutableList<Int?>,
    val nameList: MutableList<String>,
    activity: FragmentActivity,
    ): RecyclerView.Adapter<NoteAdapter.MyNoteViewHolder>() {
    private val db = Maindb.db(activity)
    private val dataModel: DataModel by activity.viewModels()
    val act = activity



    class MyNoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val txtName: TextView = itemView.findViewById(R.id.rcTxtNoteName)
        val itemCard: CardView = itemView.findViewById(R.id.rcCardNote)
        val btnDel: ImageView = itemView.findViewById(R.id.rcImgBtnNoteDel)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNoteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_note_recycle, parent, false)
        return MyNoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyNoteViewHolder, position: Int) {
        holder.txtName.text = nameList[position]

        holder.itemCard.setOnClickListener {
            dataModel.msgID.value = idList[position]
            act.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.docFragmentHolder, NoteOpenFragment.newInstance())
                    .commit()
        }

        holder.btnDel.setOnClickListener {
            val delPos = idList[position]
            scopeDef.launch { db.getDao().deleteByNoteId(delPos)}
            nameList.removeAt(position)
            idList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,itemCount)


        }



    }

    override fun getItemCount() = nameList.size

}
