package com.example.shoppinglist.di

import com.example.shoppinglist.data.db.dao.Dao
import com.example.shoppinglist.data.db.dao.ShoppingDao
import com.example.shoppinglist.data.repository.NoteRepository
import com.example.shoppinglist.data.repository.NoteRepositoryImpl
import com.example.shoppinglist.data.repository.ShoppingNoteRepository
import com.example.shoppinglist.data.repository.ShoppingNoteRepositoryImpl
import com.example.shoppinglist.domain.NoteInteractor
import com.example.shoppinglist.domain.NoteInteractorImpl
import com.example.shoppinglist.domain.ShoppingNoteInteractor
import com.example.shoppinglist.domain.ShoppingNoteInteractorImpl
//import com.example.shoppinglist.ui.screens.new_note.NewNoteViewModelFactory
import com.example.shoppinglist.ui.screens.shopping_new_note.ShoppingNewNoteViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideNoteRepository(dao: Dao):NoteRepository{
        return NoteRepositoryImpl(dao)
    }
@Provides
@Singleton
fun provideNoteInteractor(noteRepository: NoteRepository): NoteInteractor{
    return NoteInteractorImpl(noteRepository)
}
    @Provides
    @Singleton
    fun provideShoppingNoteRepository(shoppingDao: ShoppingDao): ShoppingNoteRepository {
        return ShoppingNoteRepositoryImpl(shoppingDao)
    }

    @Provides
    @Singleton
    fun provideShoppingNoteInteractor(shoppingNoteRepository: ShoppingNoteRepository): ShoppingNoteInteractor {
        return ShoppingNoteInteractorImpl(shoppingNoteRepository)
    }

//    @Provides
//    @Singleton
//    fun provideNewNoteViewModelFactory(interactor: NoteInteractor) : NewNoteViewModelFactory {
//        return NewNoteViewModelFactory(interactor)
//    }

}