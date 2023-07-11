package com.example.shoppinglist.ui.screens.shopping_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import com.example.shoppinglist.domain.ShoppingNoteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingNoteViewModel @Inject constructor(
    private val interactor: ShoppingNoteInteractor
) : ViewModel() {
    var notesStateFlow: StateFlow<List<ShoppingNoteItem>> = interactor.getShoppingListNote()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun deleteShoppingNote(id: Int) {
        viewModelScope.launch {
            interactor.deleteShoppingNote(id)
        }
    }
    fun sendQuery(title: String) {
        viewModelScope.launch {
           notesStateFlow = interactor.getShoppingListByName(title)
               .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

        }
    }
}