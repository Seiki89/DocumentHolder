package com.example.documentholder.card

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

class CardAdapter (
    val idList: MutableList<Int?>,
    val bankList: MutableList<String>,
    val nameList: MutableList<String>,
    activity: FragmentActivity,
): RecyclerView.Adapter<CardAdapter.MyCardViewHolder>() {
    private val db = Maindb.db(activity)
    private val dataModel: DataModel by activity.viewModels()
    val act = activity

    class MyCardViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val txtBank: TextView = itemView.findViewById(R.id.rcTxtServiceAdapter)
        val txtName: TextView = itemView.findViewById(R.id.rcTxtLoginAdapter)
        val itemCard: CardView = itemView.findViewById(R.id.rcCardItemAdapter)
        val btnDel: ImageView = itemView.findViewById(R.id.rcImgBtnDeleteAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_pass_card_recycle, parent, false)
        return MyCardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyCardViewHolder, position: Int) {
        holder.txtBank.text = bankList[position]
        holder.txtName.text = nameList[position]

        holder.itemCard.setOnClickListener {
            dataModel.msgID.value = idList[position]
            act.supportFragmentManager
                .beginTransaction()
                .replace(R.id.docFragmentHolder, CardOpenFragment.newInstance())
                .commit()
        }

        holder.btnDel.setOnClickListener {
            val idLP = idList[position]
            scopeDef.launch { db.getDao().deleteByCardId(idLP)}
            idList.removeAt(position)
            bankList.removeAt(position)
            nameList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,itemCount)
        }


    }

    override fun getItemCount() = idList.size
}


