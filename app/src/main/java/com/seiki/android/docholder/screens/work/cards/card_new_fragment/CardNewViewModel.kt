package com.seiki.android.docholder.screens.work.cards.card_new_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seiki.android.docholder.REPOSITORY_CARD
import com.seiki.android.docholder.model.CardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardNewViewModel:ViewModel() {
    fun insert(cardModel: CardModel) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY_CARD.insertCard(cardModel){}
        }
}