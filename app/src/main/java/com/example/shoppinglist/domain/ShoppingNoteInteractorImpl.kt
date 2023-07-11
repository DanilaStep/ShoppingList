package com.example.shoppinglist.domain

import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import com.example.shoppinglist.data.repository.NoteRepository
import com.example.shoppinglist.data.repository.ShoppingNoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingNoteInteractorImpl @Inject constructor(
    private val shoppingNoteRepository: ShoppingNoteRepository
): ShoppingNoteInteractor{
    override suspend fun insertShoppingNote(shoppingNoteItem: ShoppingNoteItem) {
        shoppingNoteRepository.insertShoppingNote(shoppingNoteItem)
    }

    override suspend fun updateShoppingNote(shoppingNoteItem: ShoppingNoteItem) {
        shoppingNoteRepository.updateShoppingNote(shoppingNoteItem)
    }

    override suspend fun deleteShoppingNote(id: Int) {
        shoppingNoteRepository.deleteShoppingNote(id)
    }

    override fun getShoppingListNote(): Flow<List<ShoppingNoteItem>> {
        return shoppingNoteRepository.getShoppingListNote()
    }
    override fun getShoppingListByName(title: String): Flow<List<ShoppingNoteItem>> {
        return shoppingNoteRepository.getShoppingListByName(title)
    }
}
