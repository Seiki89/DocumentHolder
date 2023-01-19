package com.seiki.android.docholder.database.repository.card

import androidx.lifecycle.LiveData
import com.seiki.android.docholder.model.CardModel


interface CardRepository {
    val getCard: LiveData<List<CardModel>>
    suspend fun insertCard(cardModel: CardModel, onSuccess:() -> Unit)
    suspend fun deleteCard(cardModel: CardModel, onSuccess:() -> Unit)
}