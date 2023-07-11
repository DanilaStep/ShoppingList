package com.example.shoppinglist.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppinglist.data.db.dao.Dao
import com.example.shoppinglist.data.db.dao.ShoppingDao
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import com.example.shoppinglist.domain.models.NoteModel

@Database(entities = [NoteItem::class, ShoppingNoteItem::class], version = 1)
abstract class AppDataBase :RoomDatabase() {
    abstract fun dao(): Dao
    abstract fun shoppingDao(): ShoppingDao
}