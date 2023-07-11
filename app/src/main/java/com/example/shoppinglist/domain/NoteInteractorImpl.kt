package com.example.shoppinglist.domain

import com.example.shoppinglist.data.db.dao.Dao
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import com.example.shoppinglist.data.repository.NoteRepository
import com.example.shoppinglist.domain.models.NoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteInteractorImpl @Inject
constructor(
    private val noteRepository: NoteRepository
): NoteInteractor {

    override suspend fun insertNote(noteModel: NoteModel) {
        noteRepository.insertNote(noteModel)
    }

    override suspend fun updateNote(noteModel: NoteModel) {
        noteRepository.updateNote(noteModel)
    }

    override suspend fun deleteNote(id: Int) {
        noteRepository.deleteNote(id)
    }

    override suspend fun getListNote(): List<NoteModel> {
        return noteRepository.getListNote()
    }
    override suspend fun getListByName(title: String): List<NoteModel> {
        return noteRepository.getListByName(title)
    }
}