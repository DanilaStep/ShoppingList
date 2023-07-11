package com.example.shoppinglist.data.db.dao

import android.widget.TextView
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import com.example.shoppinglist.domain.models.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    suspend fun insertNote(noteItem: NoteItem)

    @Update
    suspend fun updateNote(noteItem: NoteItem)

    @Query("DELETE FROM note_list WHERE id IS :id")
    suspend fun deleteNote(id: Int)

    @Query("SELECT * FROM note_list")
    suspend fun getListNote(): List<NoteItem>

    @Query("SELECT * FROM note_list WHERE title LIKE :title")
    suspend fun getListByName(title: String): List<NoteItem>
}



