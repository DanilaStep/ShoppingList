package com.example.shoppinglist.data.repository

import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import kotlinx.coroutines.flow.Flow

interface ShoppingNoteRepository {
    suspend fun insertShoppingNote(shoppingNoteItem: ShoppingNoteItem)

    suspend fun updateShoppingNote(shoppingNoteItem: ShoppingNoteItem)

    suspend fun deleteShoppingNote(id: Int)

    fun getShoppingListNote(): Flow<List<ShoppingNoteItem>>

    fun getShoppingListByName(title: String): Flow<List<ShoppingNoteItem>>
}