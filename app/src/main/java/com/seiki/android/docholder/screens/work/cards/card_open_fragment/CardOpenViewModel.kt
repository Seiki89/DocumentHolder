package com.seiki.android.docholder.screens.work.cards.card_open_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seiki.android.docholder.REPOSITORY_CARD
import com.seiki.android.docholder.model.CardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardOpenViewModel:ViewModel() {
    fun insert(cardModel: CardModel, onSuccess:() -> Unit ) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY_CARD.insertCard(cardModel){}
        }
}