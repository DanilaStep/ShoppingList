package com.example.shoppinglist.data.repository

import com.example.shoppinglist.data.db.dao.Dao
import com.example.shoppinglist.data.db.dao.ShoppingDao
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingNoteRepositoryImpl @Inject constructor(
    private val shoppingDao: ShoppingDao
) : ShoppingNoteRepository {
    override suspend fun insertShoppingNote(shoppingNoteItem: ShoppingNoteItem) {
        shoppingDao.insertShoppingNote(shoppingNoteItem)
    }

    override suspend fun updateShoppingNote(shoppingNoteItem: ShoppingNoteItem) {
        shoppingDao.updateShoppingNote(shoppingNoteItem)
    }

    override suspend fun deleteShoppingNote(id: Int) {
        shoppingDao.deleteShoppingNote(id)
    }

    override fun getShoppingListNote(): Flow<List<ShoppingNoteItem>> {
        return shoppingDao.getShoppingListNote()
    }
    override fun getShoppingListByName(title: String): Flow<List<ShoppingNoteItem>> {
        return shoppingDao.getShoppingListByName(title)
    }

}