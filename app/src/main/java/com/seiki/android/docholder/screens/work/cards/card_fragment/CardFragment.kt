package com.seiki.android.docholder.screens.work.cards.card_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentCardBinding
import com.seiki.android.docholder.model.CardModel
import com.seiki.android.docholder.screens.work.documents.DocumentViewModel

class CardFragment : Fragment() {
    private lateinit var bind: FragmentCardBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CardAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentCardBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[DocumentViewModel::class.java]
        viewModel.initDataBase()
        init()

    }

    fun click(cardModel: CardModel){
        val bundle = Bundle()
        bundle.putSerializable("Card",cardModel)
        APP.navController.navigate(R.id.action_cardFragment_to_cardOpenFragment, bundle)
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[CardFragmentViewModel::class.java]
        viewModel.initDataBase()

        recyclerView = bind.cardRecycle
        adapter = CardAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllCards().observe(viewLifecycleOwner) { listCards ->
            adapter.setList(listCards.asReversed())
        }


        animationStart()


        bind.txtBtnCircle.setOnClickListener {

            APP.navController.navigate(R.id.action_cardFragment_to_cardNewFragment)
            //создать новую карту
        }
    }

    private fun animationStart() {
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

