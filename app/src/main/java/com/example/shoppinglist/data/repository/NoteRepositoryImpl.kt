package com.example.shoppinglist.data.repository

import com.example.shoppinglist.data.db.dao.Dao
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import com.example.shoppinglist.domain.models.NoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: Dao
): NoteRepository {

    override suspend fun insertNote(noteModel: NoteModel) {
        dao.insertNote(toNoteItem(noteModel))
    }

    override suspend fun updateNote(noteModel: NoteModel) {
        dao.updateNote(toNoteItem(noteModel))
    }

    override suspend fun deleteNote(id: Int) {
        dao.deleteNote(id)
    }

    override suspend fun getListNote(): List<NoteModel> {
        return dao.getListNote().map { it.toNoteModel() }
    }
    override suspend fun getListByName(title: String): List<NoteModel> {
        return dao.getListByName(title).map { it.toNoteModel() }
    }
    private fun toNoteItem(noteModel: NoteModel): NoteItem{
        return NoteItem(
            id = noteModel.id,
            title = noteModel.title,
            content = noteModel.content,
            time = noteModel.time
        )
    }
}