package com.seiki.android.docholder.database.repository.card

import androidx.lifecycle.LiveData
import com.seiki.android.docholder.database.DAO
import com.seiki.android.docholder.database.repository.pass.PassRepository
import com.seiki.android.docholder.model.CardModel
import com.seiki.android.docholder.model.PassModel

class CardRealization(private val Dao: DAO) : CardRepository {
    override val getCard: LiveData<List<CardModel>>
        get() = Dao.getCard()

    override suspend fun insertCard(cardModel: CardModel, onSuccess: () -> Unit) {
        Dao.insertCard(cardModel)
    }

    override suspend fun deleteCard(cardModel: CardModel, onSuccess: () -> Unit) {
        Dao.deleteCard(cardModel)
    }
}