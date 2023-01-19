package com.seiki.android.docholder.screens.work.passwords.pass_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentPassBinding
import com.seiki.android.docholder.model.PassModel


class PassFragment : Fragment() {
    private lateinit var bind: FragmentPassBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PassAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentPassBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun click(passModel: PassModel){
        val bundle = Bundle()
        bundle.putSerializable("Pass",passModel)
        APP.navController.navigate(R.id.action_passFragment_to_passOpenFragment, bundle)
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[PassFragmentViewModel::class.java]
        viewModel.initDataBase()

        animationStart()

        recyclerView = bind.passRecycle
        adapter = PassAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllPass().observe(viewLifecycleOwner) { listPass ->
            adapter.setList(listPass.asReversed())
        }

        bind.txtBtnCircle.setOnClickListener {
            //создать новый пароль
            APP.navController.navigate(R.id.action_passFragment_to_passNewFragment)
        }
    }

    private fun animationStart() {
        //стартовая анимация
        bind.imgBtnCircle.animate().apply {
            duration = 400
            rotationYBy(360f)
        }
        bind.txtBtnCircle.animate().apply {
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
}