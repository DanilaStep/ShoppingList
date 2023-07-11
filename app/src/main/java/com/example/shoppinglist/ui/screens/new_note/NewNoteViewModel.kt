package com.example.shoppinglist.ui.screens.new_note

import androidx.lifecycle.*
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.domain.NoteInteractor
import com.example.shoppinglist.domain.models.NoteModel
import com.example.shoppinglist.utils.TimeManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.annotation.PropertyKey
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val interactor: NoteInteractor,

) : ViewModel() {



    fun notBlanc(title: String, content: String) {
        if (title.isNotBlank()) {
            insertNote(
                NoteModel(
                    title = title ,
                    content = content,
                    time = TimeManager.getCurrentTime()
                )
            )
        }
    }


     private fun insertNote(noteModel: NoteModel) {
        viewModelScope.launch {
            interactor.insertNote(noteModel)
        }
    }

     fun updateNote(noteItem: NoteModel) {
        viewModelScope.launch {
            interactor.updateNote(noteItem)
        }
    }
}



