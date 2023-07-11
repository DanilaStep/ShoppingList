package com.example.shoppinglist.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppinglist.domain.models.NoteModel
import java.io.Serializable
import java.time.Instant

@Entity (tableName = "note_list")
data class NoteItem(
    @PrimaryKey (autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo (name = "title")
    val title: String,

    @ColumnInfo (name = "content")
    val content: String,

    @ColumnInfo (name = "time")
    val time: String,

    @ColumnInfo (name = "title1")
    val title1: String = "",

    @ColumnInfo (name = "content1")
    val content1: String = "",

    @ColumnInfo (name = "time1")
    val time1: String = "",

    @ColumnInfo (name = "title2")
    val title2: String = "",

    @ColumnInfo (name = "content2")
    val content2: String = "",

    @ColumnInfo (name = "time2")
    val time2: String = ""

    ){

        fun toNoteModel(): NoteModel {
            return NoteModel(
                id = id,
                title = title,
                content = content,
                time = time
            )
        }
    }

