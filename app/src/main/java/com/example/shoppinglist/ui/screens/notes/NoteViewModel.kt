package com.example.shoppinglist.ui.screens.notes

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.repository.NoteRepository
import com.example.shoppinglist.domain.NoteInteractor
import com.example.shoppinglist.domain.models.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val interactor: NoteInteractor
) : ViewModel() {
    private val mStateFlow = MutableStateFlow<List<NoteModel>>(emptyList())

    val notesStateFlow: StateFlow<List<NoteModel>> = mStateFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        getAllNotes()
    }

    //delete
    fun deleteNote(id: Int) {
        viewModelScope.launch {
            interactor.deleteNote(id)
            getAllNotes()
        }
    }

    fun sendQuery(title: String) {
        viewModelScope.launch {
            mStateFlow.value = interactor.getListByName(title)
        }
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            mStateFlow.value = interactor.getListNote()
        }
    }
}