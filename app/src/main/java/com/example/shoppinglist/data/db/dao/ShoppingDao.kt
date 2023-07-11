package com.example.shoppinglist.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {
    @Insert
    suspend fun insertShoppingNote(shoppingNoteItem: ShoppingNoteItem)

    @Update
    suspend fun updateShoppingNote(shoppingNoteItem: ShoppingNoteItem)

    @Query("DELETE FROM shopping_list WHERE id IS :id")
    suspend fun deleteShoppingNote(id: Int)

    @Query("SELECT * FROM shopping_list")
    fun getShoppingListNote(): Flow<List<ShoppingNoteItem>>

    @Query("SELECT * FROM shopping_list WHERE title LIKE :title")
    fun getShoppingListByName(title: String): Flow<List<ShoppingNoteItem>>
}