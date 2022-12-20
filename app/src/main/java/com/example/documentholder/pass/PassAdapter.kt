package com.example.documentholder.pass

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

class PassAdapter (
    val idList: MutableList<Int?>,
    val serviceList: MutableList<String>,
    val loginList: MutableList<String>,
    activity: FragmentActivity,
): RecyclerView.Adapter<PassAdapter.MyPassViewHolder>() {
    private val db = Maindb.db(activity)
    private val dataModel: DataModel by activity.viewModels()
    val act = activity

    class MyPassViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val txtService: TextView = itemView.findViewById(R.id.rcTxtServiceAdapter)
        val txtLogin: TextView = itemView.findViewById(R.id.rcTxtLoginAdapter)
        val itemCard: CardView = itemView.findViewById(R.id.rcCardItemAdapter)
        val btnDel: ImageView = itemView.findViewById(R.id.rcImgBtnDeleteAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): MyPassViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_pass_card_recycle, parent, false)
        return MyPassViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyPassViewHolder, position: Int) {
        holder.txtService.text = serviceList[position]
        holder.txtLogin.text = loginList[position]

        holder.itemCard.setOnClickListener {
            dataModel.msgID.value = idList[position]
            act.supportFragmentManager
                .beginTransaction()
                .replace(R.id.docFragmentHolder, PassOpenFragment.newInstance())
                .commit()
        }

        holder.btnDel.setOnClickListener {
            val idLP = idList[position]
            scopeDef.launch { db.getDao().deleteByPassId(idLP)}
            idList.removeAt(position)
            serviceList.removeAt(position)
            loginList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,itemCount)
        }

    }

    override fun getItemCount() = idList.size


}