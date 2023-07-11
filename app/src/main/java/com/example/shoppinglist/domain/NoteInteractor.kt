package com.example.shoppinglist.domain

import android.content.Context
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import com.example.shoppinglist.domain.models.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteInteractor {

    suspend fun insertNote(noteModel: NoteModel)

    suspend fun updateNote(noteModel: NoteModel)

    suspend fun deleteNote(id: Int)

    suspend fun getListNote(): List<NoteModel>

    suspend fun getListByName(title: String): List<NoteModel>

}