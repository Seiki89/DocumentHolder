package com.example.documentholder.pass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.DataBase.PassDb
import com.example.documentholder.R
import com.example.documentholder.databinding.FragmentPassOpenBinding
import com.example.documentholder.misc.DataModel
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PassOpenFragment : Fragment() {
    lateinit var bind: FragmentPassOpenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentPassOpenBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Maindb.db(requireActivity())
        val dataModel: DataModel by requireActivity().viewModels()
        var idPD = 0
        dataModel.msgID.observe(activity as LifecycleOwner) { idPD = it.toString().toInt() }
        animationStart()

        scopeDef.launch {
            var service = ""
            var login = ""
            var password = ""
            db.getDao().getPass().toList().forEach {
                if (idPD == it.id){
                    service = it.service
                    login = it.login
                    password = it.password
                }
            }
            withContext(Dispatchers.Main){
                bind.txtHeader.text = service
                bind.edTxtLogin.setText(login)
                bind.edTxtPass.setText(password)
            }
        }



        bind.imgBtnCircle.setOnClickListener {exit()}

        bind.imgSaveBtn.setOnClickListener {
            scopeDef.launch {
                val service = bind.txtHeader.text.toString()
                val login = bind.edTxtLogin.text.toString()
                val password = bind.edTxtPass.text.toString()
                db.getDao().insertPass(PassDb(idPD,service,login,password))
            }
            exit()
        }

    }
    private fun exit(){
        (activity as FragmentActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.docFragmentHolder, PassFragment.newInstance())
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
        fun newInstance() = PassOpenFragment()

    }
}