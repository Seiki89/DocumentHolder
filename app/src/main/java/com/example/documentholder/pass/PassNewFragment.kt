package com.example.documentholder.pass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.DataBase.PassDb
import com.example.documentholder.R
import com.example.documentholder.databinding.FragmentPassNewBinding
import com.example.documentholder.misc.scopeDef
import kotlinx.coroutines.launch


class PassNewFragment : Fragment() {
    lateinit var bind:FragmentPassNewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentPassNewBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Maindb.db(requireActivity())
        animationStart()

        bind.imgBtnCircle.setOnClickListener {exit()}

        bind.imgSaveBtn.setOnClickListener {
            scopeDef.launch {
                val service = bind.recPassEdTxtService.text.toString()
                val login = bind.recPassEdTxtLogin.text.toString()
                val password = bind.recPassEdTxtPass.text.toString()
                db.getDao().insertPass(PassDb(null,service,login,password))
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
        fun newInstance() = PassNewFragment()

    }
}