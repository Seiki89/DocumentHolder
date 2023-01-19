package com.seiki.android.docholder.screens.work.cards.card_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.seiki.android.docholder.REPOSITORY_CARD
import com.seiki.android.docholder.database.MainDb
import com.seiki.android.docholder.database.repository.card.CardRealization
import com.seiki.android.docholder.model.CardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardFragmentViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    fun initDataBase(){
        val daoCard = MainDb.db(context).getDao()
        REPOSITORY_CARD = CardRealization(daoCard)
    }
    fun getAllCards(): LiveData<List<CardModel>> {
        return REPOSITORY_CARD.getCard
    }
    fun delete(cardModel: CardModel, onSuccess:() -> Unit ) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY_CARD.deleteCard(cardModel){}
        }
}